package com.example.optly.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.optly.R

class FileManagementFragment : Fragment() {

    private val PICK_FILE_REQUEST = 1001

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_filemanager, container, false)

        // Example: attach file picker to a button in your layout
        val pickFileBtn = view.findViewById<Button>(R.id.pickFileBtn)
        pickFileBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "*/*" // any file type
            }
            startActivityForResult(intent, PICK_FILE_REQUEST)
        }

        return view
    }

    @Deprecated("Deprecated in Activity Result API, consider using registerForActivityResult")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_FILE_REQUEST && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri: Uri ->
                // You now have a file Uri
                // Example: show it in logs or send to another method
                println("Picked file: $uri")
            }
        }
    }
}
