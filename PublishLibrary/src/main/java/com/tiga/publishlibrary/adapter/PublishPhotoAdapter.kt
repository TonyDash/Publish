package com.tiga.publishlibrary.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tiga.publishlibrary.R
import com.tiga.publishlibrary.utils.GlideUtils
import com.tiga.publishlibrary.utils.UIUtils

class PublishPhotoAdapter(context: Activity, mList: MutableList<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val ITEM_TYPE_EMPTY = 0x00001
        const val ITEM_TYPE_MULTI_PHOTO = 0x00002
    }

    private var context: Activity? = context
    private var itemScreenWidth: Int = 0
    private var dataList: MutableList<String> = mutableListOf()
    private var onDataChangeListener: OnDataChangeListener? = null

    init {
        dataList.addAll(mList)
        itemScreenWidth = (UIUtils.getScreenWidth(context) - UIUtils.dip2Px(context, 30)) / 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_TYPE_EMPTY -> {
                EmptyHolder(
                    LayoutInflater.from(context).inflate(R.layout.item_release_empty, parent, false)
                )
            }
            ITEM_TYPE_MULTI_PHOTO -> {
                PhotoHolder(
                    LayoutInflater.from(context).inflate(R.layout.item_release_photo, parent, false)
                )
            }
            else -> {
                EmptyHolder(
                    LayoutInflater.from(context).inflate(R.layout.item_release_empty, parent, false)
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return if (listSize() >= 9) {
            dataList.size
        } else {
            dataList.size + 1
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is EmptyHolder -> {
                bindEmptyItemHolder(holder, position)
            }
            is PhotoHolder -> {
                bindPhotoItemHolder(holder, position)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (listSize() >= 9) {
            ITEM_TYPE_MULTI_PHOTO
        } else {
            if (position + 1 == itemCount) {
                ITEM_TYPE_EMPTY
            } else {
                ITEM_TYPE_MULTI_PHOTO
            }
        }
    }


    private fun bindEmptyItemHolder(holder: EmptyHolder, position: Int) {
        holder.mRlEmptyPhoto?.run {
            if (listSize() >= 9) { //集合长度大于等于9张时，隐藏 图片
                this.visibility = View.GONE
            } else {
                this.visibility = View.VISIBLE
            }
//        this.setOnClickListener { showAddPhotoDialog() }
        }
    }

    private fun bindPhotoItemHolder(holder: PhotoHolder, position: Int) {
        val urlPath: String = dataList[position]
        if (urlPath.endsWith("gif")) {
            holder.tvGifTag!!.visibility = View.VISIBLE
            context?.let { GlideUtils.changeGifToPicture(it, dataList[position], holder.mIvPhoto) }
        } else {
            holder.tvGifTag!!.visibility = View.GONE
            context?.let { GlideUtils.loadRound(it, dataList[position], holder.mIvPhoto) }
        }
        holder.mIvDelete!!.setOnClickListener {
            dataList.removeAt(position)
            onDataChangeListener?.onDataChange()
            notifyDataSetChanged()
        }
        holder.mIvPhoto!!.setOnClickListener {

        }
    }

    //对外暴露方法,点击添加图片
    fun addMoreItem(newData: List<String>) {
        dataList.addAll(newData)
        onDataChangeListener?.onDataChange()
        notifyDataSetChanged()
    }

    fun addItem(newData: String) {
        dataList.add(newData)
        onDataChangeListener?.onDataChange()
        notifyDataSetChanged()
    }

    //得到集合长度
    private fun listSize(): Int {
        return dataList.size
    }


    interface OnDataChangeListener {
        fun onDataChange()
    }

    fun setOnDataChangeListener(mOnDataChangeListener: OnDataChangeListener) {
        this.onDataChangeListener = mOnDataChangeListener
    }

    inner class EmptyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mRlEmptyPhoto: RelativeLayout? = null

        init {
            val params: RelativeLayout.LayoutParams =
                RelativeLayout.LayoutParams(itemScreenWidth, itemScreenWidth)
            val margins = context?.let { UIUtils.dip2Px(it, 1) } ?: 1
            params.setMargins(margins, margins, margins, margins)
            itemView.layoutParams = params
            mRlEmptyPhoto = itemView.findViewById(R.id.rlAddPhoto)
        }
    }

    inner class PhotoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mIvPhoto: ImageView? = null
        var mIvDelete: ImageView? = null
        var tvGifTag: TextView? = null

        init {
            val params: RelativeLayout.LayoutParams =
                RelativeLayout.LayoutParams(itemScreenWidth, itemScreenWidth)
            val margins = context?.let { UIUtils.dip2Px(it, 1) } ?: 1
            params.setMargins(margins, margins, margins, margins)
            itemView.layoutParams = params
            mIvPhoto = itemView.findViewById(R.id.iv_photo)
            mIvDelete = itemView.findViewById(R.id.iv_delete_photo)
            tvGifTag = itemView.findViewById(R.id.tvGifTag)
        }
    }
}