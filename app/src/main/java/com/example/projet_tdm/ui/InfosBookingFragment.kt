package com.example.projet_tdm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.fragment.app.Fragment
import com.example.projet_tdm.R
import com.google.zxing.WriterException
import kotlinx.android.synthetic.main.fragment_infos_booking.*


class InfosBookingFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_infos_booking, container, false)

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val data: String = "1234567899"
        if (data.isEmpty()) {
            Toast.makeText(context,"Error",Toast.LENGTH_LONG).show()
        } else {
                val qrgEncoder = QRGEncoder(data, null, QRGContents.Type.TEXT, 500)
                try {
                    val qrBits = qrgEncoder.encodeAsBitmap()
                    qrCode.setImageBitmap(qrBits)
                } catch (e: WriterException) {
                    e.printStackTrace()
                }
        }



    }


}