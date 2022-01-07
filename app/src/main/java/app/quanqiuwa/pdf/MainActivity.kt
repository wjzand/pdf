package app.quanqiuwa.pdf

import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.itextpdf.text.*
import com.itextpdf.text.pdf.*
import java.io.File
import java.io.FileOutputStream


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            val bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            val titlefont = Font(bfChinese, 16f, Font.BOLD);

            val document = Document()

            val p = Environment.getExternalStorageDirectory().absolutePath + File.separator + packageName
            val pF = File(p)
            if(!pF.exists()){
                pF.mkdir()
            }
            val s =  pF.absolutePath + File.separator + "test.pdf"

            val sF = File(s)
            if(!sF.exists()){
                sF.createNewFile()
            }

            val pdfWriter = PdfWriter.getInstance(document,FileOutputStream(s))

            document.open()

            val title = Paragraph("pdf test 标题",titlefont)
            title.alignment = 1
            title.spacingBefore = 10f
            title.spacingAfter = 10f
            document.add(title)


          /*  val pdfTable =  PdfPTable(3)
            pdfTable.widthPercentage = 100f
            pdfTable.spacingBefore = 10f
            pdfTable.spacingAfter = 10f

            val listRow: MutableList<PdfPRow> = pdfTable.rows
            val columnWidths = floatArrayOf(3f,3f,3f)
            pdfTable.setWidths(columnWidths)

            val cells1 = arrayOfNulls<PdfPCell>(3)
            for(i in 0..2){
                cells1[i] = PdfPCell(Phrase("合同编号:$i",titlefont))
                cells1[i]?.run {
                    verticalAlignment = Element.ALIGN_MIDDLE
                    horizontalAlignment = Element.ALIGN_CENTER
                    fixedHeight = 60f
                }
            }
            val row1 = PdfPRow(cells1)
            listRow.add(row1)*/

            val pdfTableLeft =  PdfPTable(2)
            pdfTableLeft.widthPercentage = 100f
            pdfTableLeft.spacingBefore = 10f
            pdfTableLeft.spacingAfter = 10f

            val listRow: MutableList<PdfPRow> = pdfTableLeft.rows
            val columnWidths = floatArrayOf(3f,3f)
            pdfTableLeft.setWidths(columnWidths)

            val left = PdfPCell(Phrase("合同编号:",titlefont))
            left?.run {
                verticalAlignment = Element.ALIGN_MIDDLE
                horizontalAlignment = Element.ALIGN_CENTER
                fixedHeight = 60f
            }

            val pdfTableRight =  PdfPTable(1)
            pdfTableRight.widthPercentage = 100f

            val cells1 = arrayOfNulls<PdfPCell>(3)
            for(i in 0..2){
                cells1[i] = PdfPCell(Phrase("编号:$i",titlefont))
                cells1[i]?.run {
                    verticalAlignment = Element.ALIGN_MIDDLE
                    horizontalAlignment = Element.ALIGN_CENTER
                    fixedHeight = 20f
                }
                pdfTableRight.addCell(cells1[i])
            }

            val right = PdfPCell()
            right.border = 0
            right.paddingTop = 0f
            right.paddingBottom = 0f
            right.paddingLeft = 0f
            right.addElement(pdfTableRight)

            val row1 = PdfPRow(arrayOf(left,right))
            listRow.add(row1)
            document.add(pdfTableLeft)


            document.close()
            pdfWriter.close()

        } catch (e: Exception) {
            Log.e("wjz",e.toString())
        } finally {
        }
    }
}