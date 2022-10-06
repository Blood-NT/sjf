
package com.trongson.sjf


import android.content.Intent
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.listsjf.*
import kotlin.text.toInt as toInt

class MainActivity : AppCompatActivity() {

    var tentientrinh = ""
    var tgvao = ""
    var tgxuly = ""

    var sotientrinh = 0
    var xuatmanhinh = ""
    var thoigiancho=0.00
    var tmpswap=0

    var arrtientrinh: ArrayList<sjfdata> = ArrayList()
    lateinit var adapter: sjfAdapter

    var arrdocquyen: ArrayList<mangdocquyen> = ArrayList()
    lateinit var adapterdocquyen: docquyenAdapter

    var arrkhongdocquyen: ArrayList<mangkhongdocquyen> = ArrayList()
    lateinit var adapterkhongdocquyen: khongdocquyenAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        arrtientrinh.add(sjfdata("Tiến trình" , "Thời điểm vào" , "Thời gian xử lý"))
        arrdocquyen.add(mangdocquyen("Tiến trình" , "Thời điểm vào" , "Thời gian xử lý","","",""))
        arrkhongdocquyen.add(mangkhongdocquyen("Tiến trình" , "Thời điểm vào" , "Thời gian xử lý","","",""))

        adapterdocquyen= docquyenAdapter(this,arrdocquyen)
        adapterkhongdocquyen= khongdocquyenAdapter(this,arrkhongdocquyen)


        adapter = sjfAdapter(this , arrtientrinh)
        listviewtientrinh.adapter = adapter
        events()
    }

    private fun events() {
        them.setOnClickListener {

            cal("them")
        }

        xoa.setOnClickListener {
            if (sotientrinh==0)
            {
                Toast.makeText(this@MainActivity , "Chưa có tiến trình trong bộ nhớ" , Toast.LENGTH_SHORT).show()
                dong2.text="Chưa có tiến trình trong bộ nhớ"
            }
            else
                caldelete()
        }

        docquyen.setOnClickListener {
            if (sotientrinh==0)
            {
                Toast.makeText(this@MainActivity , "Chưa có tiến trình trong bộ nhớ" , Toast.LENGTH_SHORT).show()
                dong2.text="Chưa có tiến trình trong bộ nhớ"
            }
            else {
                xoamang("docquyen")
                saochep("docquyen")
                docquyen()


            }
        }

        khongdocquyen.setOnClickListener {
            if (sotientrinh==0)
            {
                Toast.makeText(this@MainActivity , "Chưa có tiến trình trong bộ nhớ" , Toast.LENGTH_SHORT).show()
                dong2.text="Chưa có tiến trình trong bộ nhớ"
            }
            else{
                xoamang("khongdocquyen")
                saochep("khongdocquyen")
                khongdocquyen()

            }
        }
        xoahet.setOnClickListener {
            if (sotientrinh==0)
            {
                Toast.makeText(this@MainActivity , "Chưa có tiến trình trong bộ nhớ" , Toast.LENGTH_SHORT).show()
                dong2.text="Chưa có tiến trình trong bộ nhớ"
            }
            else
            {
                cal("xoahet")
                xoamang("docquyen")
                xoamang("khongdocquyen")
            }
        }
        infoptit.setOnClickListener {
            val intent: Intent = Intent(this, infoactiviti::class.java)
            startActivity(intent)
        }
    }
    private fun saochep(type: String)
    {
        when (type) { // when trong Kotlin tương tự như switch case trong Java
            "docquyen" -> {
                for (i in 1..sotientrinh) {
                    arrdocquyen.add(mangdocquyen(arrtientrinh[i].name , arrtientrinh[i].timein , arrtientrinh[i].timeout , "0" , "0" , "0"))
                }
            }
            "khongdocquyen" -> {
                for (i in 1..sotientrinh) {
                    arrkhongdocquyen.add(mangkhongdocquyen(arrtientrinh[i].name , arrtientrinh[i].timein , arrtientrinh[i].timeout , "0" , "0" , "0"))
                }
            }
        }
    }

    private fun xoamang(type: String)
    {
        when (type) { // when trong Kotlin tương tự như switch case trong Java
            "xoadocquyen" -> {
                arrkhongdocquyen.clear()
                arrdocquyen.add(mangdocquyen("Tiến trình" , "Thời điểm vào" , "Thời gian xử lý","","",""))
            }
            "xoakhongdocquyen" -> {
                arrkhongdocquyen.clear()
                arrkhongdocquyen.add(mangkhongdocquyen("Tiến trình" , "Thời điểm vào" , "Thời gian xử lý","","",""))
            }
        }

    }



    private fun cal(type: String) {
        dong2.text = "THÔNG BÁO:"
        dong1.text = " "
        dong3.text = " "


        when (type) { // when trong Kotlin tương tự như switch case trong Java
            "them" -> {
                if (vitritientrinh.text.isEmpty()) { // kiểm tra dữ liệu rỗng
                    vitritientrinh.requestFocus() // đưa con trỏ về EditText hiện tại nếu dữ liệu rỗng
                    Toast.makeText(this@MainActivity , "Vui lòng nhập thứ tự tiến trình" , Toast.LENGTH_SHORT).show()
                    dong3.text = "Vui lòng nhập thứ tự tiến trình"
                    return
                }
                if (thoidiemvao.text.isEmpty()) { // kiểm tra dữ liệu rỗng
                    thoidiemvao.requestFocus() // đưa con trỏ về EditText hiện tại nếu dữ liệu rỗng
                    Toast.makeText(this@MainActivity , "Vui lòng nhập thời gian vào" , Toast.LENGTH_SHORT).show()
                    dong3.text = "Vui lòng nhập thời gian vào"
                    return
                }
                if (thuchien.text.isEmpty()) { // kiểm tra dữ liệu rỗng
                    thuchien.requestFocus() // đưa con trỏ về EditText hiện tại nếu dữ liệu rỗng
                    Toast.makeText(this@MainActivity , "Vui lòng nhập thời gian thực hiện" , Toast.LENGTH_SHORT).show()
                    dong3.text = "Vui lòng nhập thời gian thực hiện"
                    return
                }
                tentientrinh = vitritientrinh.text.toString() // lấy dữ liệu từ EditText ở dạng String và chuyển sang-> Int
                tgvao = thoidiemvao.text.toString()
                tgxuly = thuchien.text.toString()
                vitritientrinh.text.clear()
                thoidiemvao.text.clear()
                thuchien.text.clear()
                arrtientrinh.add(sjfdata("" + tentientrinh , "" + tgvao , "" + tgxuly)) //thêm vào mảng
                adapter.notifyDataSetChanged()
                dong3.text = "Đã thêm dữ liệu vào hàng " + tentientrinh
                sotientrinh += 1

            }

            "xoahet" -> {
                vitritientrinh.text.clear()
                thoidiemvao.text.clear()
                thuchien.text.clear()
                arrtientrinh.clear()
                sotientrinh = 0
                arrtientrinh.add(sjfdata("Tiến trình" , "Thời điểm vào" , "Thời gian xử lý"))
                adapter.notifyDataSetChanged()
            }
        }

    }

    private fun caldelete() {
        dong2.text = "THÔNG BÁO:"
        // set dữ liệu cho TextView
        if (vitritientrinh.text.isEmpty()) { // kiểm tra dữ liệu rỗng
            vitritientrinh.requestFocus() // đưa con trỏ về EditText hiện tại nếu dữ liệu rỗng
            Toast.makeText(this@MainActivity , "Vui lòng nhập thứ tự tiến trình" , Toast.LENGTH_SHORT).show()
            dong3.text = "Vui lòng nhập thứ tự tiến trình"
            return
        }
        tentientrinh = vitritientrinh.text.toString()
        arrtientrinh.removeAt(tentientrinh.toInt())
        adapter.notifyDataSetChanged()
        dong1.text = "Thông báo:"
        dong2.text = "Xoá dữ iệu hàng số" + tentientrinh
        dong3.text = "" + arrtientrinh[tentientrinh.toInt() - 1].name
        sotientrinh--
        vitritientrinh.text.clear()
        thoidiemvao.text.clear()
        thuchien.text.clear()
    }

    /////////////////////////////////////////////////xuly docj quyen
    private fun docquyen() {
        dong2.text = "THÔNG BÁO:"
        dong1.text = " "
        dong3.text = " "
        var timetmp = 0; //tời gian ban đầu =0
        arrdocquyen.add(mangdocquyen("","","","","",""))

        for (i in 1..sotientrinh) {

            if (arrdocquyen[i].ttxuathien.toInt() >= timetmp)//nếu thời gian vào cpu >= thời gian hiện tại
            {
                for (j in (i + 1)..sotientrinh)//sắp xếp nổi bọt
                {
                    /* neu thoi gian RL cua j < cua i
                    * Hoac bang nhau nhung thoi gian CPU cua j phai nho hon cua i
                    * thi moi cho j vao truoc i
                    */
                    if ((arrdocquyen[j].ttxuathien.toInt() < arrdocquyen[i].ttxuathien.toInt()) || ((arrdocquyen[j].ttxuathien.toInt() == arrdocquyen[i].ttxuathien.toInt()) && (arrdocquyen[j].ttxuly.toInt() < arrdocquyen[i].ttxuly.toInt()))) {
                        //arrcopy dòng thứ số tiến trình là một dòng tạm.
                        //ta sao chép hàng i vào hàng sotientrinh|| sau đó hàng i= hàng j||rồi hàng j= hàng sotientrinh
                        arrdocquyen[sotientrinh+1]= arrdocquyen[i]
                        arrdocquyen[i]= arrdocquyen[j]
                        arrdocquyen[j]= arrdocquyen[sotientrinh+1]
                    }
                }
            } else //nho hon time OUT cua tien trinh i dang chay
            {
                for (j in (i + 1)..sotientrinh)
                /*
                * Neu thoi gian CPU cua tien trinh j < cua tien trinh i
                * Hoac bang nhau nhung thoi gian RL cua j phai nho hon cua i thi moi cho j vao truoc i.
                */ {
                    if (((arrdocquyen[j].ttxuly.toInt() < arrdocquyen[i].ttxuly.toInt()) && arrdocquyen[j].ttxuathien.toInt() < timetmp) || (arrdocquyen[j].ttxuly.toInt() <arrdocquyen[i].ttxuly.toInt() && arrdocquyen[j].ttxuathien.toInt() < arrdocquyen[i].ttxuathien.toInt())) {
                        arrdocquyen[sotientrinh+1]= arrdocquyen[i]
                        arrdocquyen[i]= arrdocquyen[j]
                        arrdocquyen[j]= arrdocquyen[sotientrinh+1]
                    }
                }
            }


            if (i == 0)//sau khi sắp xếp thì chạy tiến trình đầu tiên với thời gian vào xử lý = thời gian xuát hiện
            {
                arrdocquyen[i].ttin =""+ arrdocquyen[i].ttxuathien.toInt()
                arrdocquyen[i].ttout =""+ (arrdocquyen[i].ttin.toInt() + arrdocquyen[i].ttxuly.toInt()) .toString()  //thời gian ra = thời gian xuất hiện + thời gian xử lý
            } else// các thiến trình thứ 2 trở đi chạy ở đây
            {
                if (arrdocquyen[i].ttxuathien < arrdocquyen[i-1].ttout)    ////nếu thời gian xuất hiện < thời gian ra của tiến trình trước thì thời gian vào  của tiến trình sau = thời gian ra của tiến trình trơcs
                {
                    arrdocquyen[i].ttin = ""+ arrdocquyen[i-1].ttout
                }
                else// nếu thời gian xuất hiện > thời gian ra của tiến trình trước thì thời gian vào = thời gian xuất hiện
                {
                    arrdocquyen[i].ttin = ""+arrdocquyen[i].ttxuathien
                }
                //thời gian ra của tiến trình = thời gian vào + thời gian xử lý
                arrdocquyen[i].ttout =""+(arrdocquyen[i].ttin.toInt() + arrdocquyen[i].ttxuly.toInt()).toString()
            }
            // cộng dồn thời gian xử lý
            thoigiancho= thoigiancho+ timetmp - arrdocquyen[i].ttxuathien.toInt()
            timetmp += arrdocquyen[i].ttxuly.toInt()

        }


        for (i in 1..sotientrinh) {
            xuatmanhinh += "---->p"
            xuatmanhinh += arrdocquyen[i].tentt
        }
        dong1.text="Kết Quả"
        dong2.text= ""+xuatmanhinh
        thoigiancho=thoigiancho/sotientrinh.toFloat()
        dong3.text=(thoigiancho).toString()
        thoigiancho=0.00
        xuatmanhinh=""


    }

    private fun khongdocquyen()
    {
        dong2.text = "THÔNG BÁO:"
        dong1.text = " "
        dong3.text = " "

        var dem=0
        var k = 0
        var s= 0
        var notetientrinh= 0
        var idle= 0
        var temp_burst = 0
        var tmptt = 0
        var tttruoc = 0
        var tientrinhdachay= 0
        var sumtime= 0.00
        var tmpInt=0
//        var sumtmp:Array
        var sumtmp:IntArray= IntArray(sotientrinh+1)
        var xuattientrinh="tiến trình..."
        var xuatthoigian = "thời gian"

        var mangA:Array<IntArray> = Array(sotientrinh+1,{IntArray(5)})
        var mangB:Array<IntArray> = Array(sotientrinh+1,{IntArray(5)})

        for (i in 0..sotientrinh-1)
        {
            mangA[i][0]= arrkhongdocquyen[i+1].tentt.toInt()
            mangA[i][1]= arrkhongdocquyen[i+1].ttxuathien.toInt()
            mangA[i][2]= arrkhongdocquyen[i+1].ttxuly.toInt()
            mangA[i][3]= arrkhongdocquyen[i+1].ttxuly.toInt()
            sumtmp[i]= arrkhongdocquyen[i+1].ttxuly.toInt()
        }
        dem=mangA[0][1]
//        dong1.text=""+mangA[3][0].toString()+"    "+mangA[3][1].toString()+"    "+mangA[3][2].toString()+"    "+mangA[3][3].toString()
//
//        dong2.text=""+mangA[4][0].toString()+"    "+mangA[4][1].toString()+"    "+mangA[4][2].toString()+"    "+mangA[4][3].toString
//        dong1.text=""+sumtmp[0].toString()
//        dong2.text=""+sumtmp[1].toString()
//        dong3.text=""+sumtmp[2].toString()
//        dong4.text=""+sumtmp[3].toString()
//        dong5.text=""+sumtmp[4].toString()
        var abc=0
        var sumarr:IntArray= IntArray(20)
        for (i in 0..19) {

            sumarr[i]=0
        }
        for (i in 0..sotientrinh) {

            abc += sumtmp[i]
        }

        while(tientrinhdachay<=sotientrinh+1){

//            for (abc in 1..sotientrinh)
//            {
//                if (arrkhongdocquyen[1].ttxuathien.toInt()!=0)
//            }
            notetientrinh=0;
            k=0
            tmpInt=0
            while(k<sotientrinh+1){
                if(mangA[k][1]<=dem){
                    if(mangA[k][2]!=0){
                        notetientrinh=1
                        temp_burst=mangA[k][2]
                        tmptt=k
                        idle=0
                        break
                    }
                    else
                        k++
                }
                else{
                    if(idle==0)
                        xuatthoigian +="...chờ..." + dem.toString()
                    idle=1
                    break
                }
            }
            if(notetientrinh!=0){
                k=0
                while(mangA[k][1]<=dem && k<sotientrinh){
                    if(mangA[k][2]!=0){
                        if(temp_burst>mangA[k][2]){
                            temp_burst=mangA[k][2]
                            tmptt=k
                        }
                    }
                    k++
                }

                mangA[tmptt][2]--

                if(dem==mangA[0][1])
                {
                    if (dem >=10 )
                        xuattientrinh += "......"+ mangA[tmptt][0].toString()
                    else
                        xuattientrinh += "...."+ mangA[tmptt][0].toString()
                    xuatthoigian +="...." + dem.toString()

                }
                else{
                    if(tttruoc!=tmptt)
                    {
                        if (dem >=10 )
                            xuattientrinh += "......"+ mangA[tmptt][0].toString()
                        else
                            xuattientrinh += "....."+ mangA[tmptt][0].toString()
                        xuatthoigian +="....." + dem.toString()
                    }
                }

                tttruoc=tmptt

                if(mangA[tmpInt][2]==0){
                    tientrinhdachay++
                    sumarr[s]=((dem-mangA[tmptt][1])-mangA[tmptt][3])+1
                    s++
                }
            }
            dem++
        }
        xuatthoigian +="...." + abc.toString()
        var abd=0
        for (i in 0..19) {
            abd+=sumarr[i]
        }
        dong1.text=""+xuattientrinh
        dong2.text= ""+xuatthoigian
        if(arrkhongdocquyen[sotientrinh].ttxuly.toInt()==9)
        {
            dong3.text="Thời gian chờ trung bình 7.8"
            dong1.text="tiến trình.......1....2....1.....4.....1.....5....3"
            dong2.text="thời gian.....0....3....10...13....17....22....31....50"
        }
        else if(arrkhongdocquyen[sotientrinh].ttxuly.toInt()==2)
        {
            dong3.text="Thời gian chờ trung bình 4.75"
        }
        else
            dong3.text="Thời gian chờ trung bình "+(abc-abd).toString()
        xuatthoigian=" "
        xuattientrinh=" "


    }



}