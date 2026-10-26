package com.example.event

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtMssv: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPhone: EditText
    private lateinit var rgGender: RadioGroup
    private lateinit var btnRegister: Button
    private lateinit var checkBoxes: List<CheckBox>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtName = findViewById(R.id.edtName)
        edtMssv = findViewById(R.id.edtMssv)
        edtEmail = findViewById(R.id.edtEmail)
        edtPhone = findViewById(R.id.edtPhone)
        rgGender = findViewById(R.id.rgGender)
        btnRegister = findViewById(R.id.btnRegister)
        checkBoxes = listOf(
            findViewById(R.id.cbFacebook),
            findViewById(R.id.cbInstagram),
            findViewById(R.id.cbTiktok),
            findViewById(R.id.cbZalo)
        )

        setupEditTextEvents()
        setupRadioEvents()
        setupCheckBoxEvents()

        btnRegister.setOnClickListener { onRegister() }
    }

    // ---- Event cho EditText: toast khi focus và khi nhập ----
    private fun setupEditTextEvents() {
        val fields = mapOf(
            edtName to "Họ và tên",
            edtMssv to "Mã số sinh viên",
            edtEmail to "Email",
            edtPhone to "Số điện thoại"
        )
        fields.forEach { (edt, label) ->
            edt.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) toast("Đang nhập $label")
            }
            edt.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, st: Int, c: Int, a: Int) {}
                override fun onTextChanged(s: CharSequence?, st: Int, b: Int, c: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    if (!s.isNullOrEmpty()) toast("$label: $s")
                }
            })
        }
    }

    // ---- Event cho RadioButton ----
    private fun setupRadioEvents() {
        rgGender.setOnCheckedChangeListener { group, checkedId ->
            val rb = group.findViewById<RadioButton>(checkedId)
            toast("Giới tính: ${rb.text}")
        }
    }

    // ---- Event cho CheckBox ----
    private fun setupCheckBoxEvents() {
        checkBoxes.forEach { cb ->
            cb.setOnCheckedChangeListener { _, isChecked ->
                toast(if (isChecked) "Đã chọn ${cb.text}" else "Bỏ chọn ${cb.text}")
            }
        }
    }

    // ---- Bấm đăng kí -> sang màn hình chi tiết ----
    private fun onRegister() {
        val name = edtName.text.toString().trim()
        val mssv = edtMssv.text.toString().trim()
        val email = edtEmail.text.toString().trim()
        val phone = edtPhone.text.toString().trim()

        if (name.isEmpty() || mssv.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            toast("Vui lòng nhập đầy đủ thông tin")
            return
        }
        val checkedId = rgGender.checkedRadioButtonId
        if (checkedId == -1) {
            toast("Vui lòng chọn giới tính")
            return
        }
        val gender = findViewById<RadioButton>(checkedId).text.toString()
        val socials = checkBoxes.filter { it.isChecked }.map { it.text.toString() }

        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra("name", name)
            putExtra("mssv", mssv)
            putExtra("email", email)
            putExtra("phone", phone)
            putExtra("gender", gender)
            putExtra("socials", if (socials.isEmpty()) "Không chọn" else socials.joinToString(", "))
        }
        startActivity(intent)
    }

    private fun toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}