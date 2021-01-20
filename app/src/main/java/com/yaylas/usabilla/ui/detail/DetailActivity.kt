package com.yaylas.usabilla.ui.detail

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.yaylas.usabilla.R
import com.yaylas.usabilla.databinding.ActivityDetailBinding
import com.yaylas.usabilla.domain.DataState
import com.yaylas.usabilla.domain.model.Feedback
import com.yaylas.usabilla.domain.model.FeedbackStatus
import com.yaylas.usabilla.domain.model.FeedbackType
import com.yaylas.usabilla.domain.model.LocationAddress
import com.yaylas.usabilla.util.GlideImageGetter
import com.yaylas.usabilla.util.Util
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.*
import kotlin.collections.ArrayList

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by viewModels()

    lateinit var binding: ActivityDetailBinding

    var feedback: Feedback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        extractFeedback()
        subscribeObservers()
        setUpViews(savedInstanceState)
        fillData()
    }

    private fun setUpViews(savedInstanceState: Bundle?) {
        setUpToolbar()
        setUpRecyclerView()
        initMap(savedInstanceState)
    }

    private fun fillData() {
        setFeedbackDetails()
        setHtmlSnippet()
    }

    private fun extractFeedback() {
        feedback = intent.getParcelableExtra(KEY_FEEDBACK_OBJECT)
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, { dataState ->
            when (dataState) {
                is DataState.Success<LocationAddress> -> addressReceived(dataState.data)
                is DataState.Error -> addressErrorOccurred()
                is DataState.Loading -> {
                }
            }
        })
    }

    private fun setUpToolbar() {
        feedback?.apply {
            with(info) {
                if (feedbackType != FeedbackType.None) {
                    title = getString(
                        R.string.detail_title,
                        getString(Util.getFeedbackTypeTitle(feedbackType))
                    )
                    supportActionBar?.setBackgroundDrawable(getActionBarBackground(feedbackType))
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    private fun initMap(savedInstanceState: Bundle?) {
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync { googleMap -> onMapReady(googleMap) }
    }


    private fun setFeedbackDetails() {
        feedback?.apply {
            tvEmail.text = user.email
            tvDate.text = creationDate
            tvComment.text = info.comment
            ratingBar.rating = info.rating.toFloat()
            Glide.with(this@DetailActivity)
                .load(images.detail)
                .placeholder(R.drawable.ic_placeholder)
                .into(ivImage)
        }
        recyclerView.adapter = DetailAdapter(createDetailItems())

    }


    private fun setHtmlSnippet() {
        feedback?.apply {
            if (!TextUtils.isEmpty(htmlSnippet)) {
                tvHtmlSnippet.text = HtmlCompat.fromHtml(
                    htmlSnippet,
                    HtmlCompat.FROM_HTML_MODE_LEGACY,
                    GlideImageGetter(tvHtmlSnippet),
                    null
                )
                tvHtmlSnippet.movementMethod = LinkMovementMethod.getInstance()
                tvHtmlSnippet.visibility = View.VISIBLE
            }
        }
    }

    private fun addressReceived(address: LocationAddress) {
        with(address) {
            "$country - $city".also { tvLocation.text = it }
        }
        tvLocation.visibility = View.VISIBLE
    }

    private fun addressErrorOccurred() {
        tvLocation.visibility = View.GONE
    }


    private fun getActionBarBackground(feedbackType: FeedbackType): ColorDrawable {
        return ColorDrawable(
            ContextCompat.getColor(
                this,
                Util.getFeedbackTypeColorResId(feedbackType)
            )
        )
    }

    private fun onMapReady(googleMap: GoogleMap) {
        feedback?.apply {
            val location = LatLng(user.latitude, user.longitude)
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 12f))
            val markerOptions =
                MarkerOptions()
                    .position(location)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_pin))
            googleMap.addMarker(markerOptions)
            viewModel.setStateEvent(DetailStateEvent.ReverseGeoCode(user.latitude, user.longitude))
        }

    }

    private fun createDetailItems(): List<DetailInformationItem> {
        val result = ArrayList<DetailInformationItem>()
        feedback?.apply {
            result.add(DetailInformationItem(getString(R.string.platform_title), browser.platform))
            result.add(DetailInformationItem(getString(R.string.browser_title), browser.name))
            result.add(DetailInformationItem(getString(R.string.browser_version), browser.version))
            result.add(DetailInformationItem(getString(R.string.ip_address_title), user.ipAddress))
            result.add(DetailInformationItem(getString(R.string.labels_title), info.labels))
            result.add(
                DetailInformationItem(
                    getString(R.string.performance_title),
                    info.performance
                )
            )
            val starredLabel =
                if (starred) getString(R.string.yes_title) else getString(R.string.no_title)
            result.add(DetailInformationItem(getString(R.string.starred_title), starredLabel))

            val statusLabel = when (status) {
                FeedbackStatus.New -> getString(R.string.new_title)
                FeedbackStatus.Read -> getString(R.string.read_title)
            }
            result.add(DetailInformationItem(getString(R.string.status_title), statusLabel))
        }
        return result
    }


    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onPause() {
        mapView.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mapView.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }


    companion object {
        const val KEY_FEEDBACK_OBJECT = "KEY_FEEDBACK_OBJECT"
    }
}

@ExperimentalCoroutinesApi
private val DetailActivity.tvEmail: TextView
    get() {
        return binding.tvEmail
    }

@ExperimentalCoroutinesApi
private val DetailActivity.ratingBar: RatingBar
    get() {
        return binding.ratingBar
    }

@ExperimentalCoroutinesApi
private val DetailActivity.tvDate: TextView
    get() {
        return binding.tvDate
    }

@ExperimentalCoroutinesApi
private val DetailActivity.tvComment: TextView
    get() {
        return binding.tvComment
    }

@ExperimentalCoroutinesApi
private val DetailActivity.recyclerView: RecyclerView
    get() {
        return binding.recyclerView
    }

@ExperimentalCoroutinesApi
private val DetailActivity.ivImage: ImageView
    get() {
        return binding.ivImage
    }

@ExperimentalCoroutinesApi
private val DetailActivity.mapView: MapView
    get() {
        return binding.mapView
    }

@ExperimentalCoroutinesApi
private val DetailActivity.tvLocation: TextView
    get() {
        return binding.tvLocation
    }

@ExperimentalCoroutinesApi
private val DetailActivity.tvHtmlSnippet: TextView
    get() {
        return binding.tvHtmlSnippet
    }

