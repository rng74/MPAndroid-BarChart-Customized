package comers_0890.httpsvk.myapplication

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bar_chart.legend.isEnabled = false
        bar_chart.description.isEnabled = false
        bar_chart.axisRight.isEnabled = false
        bar_chart.axisLeft.isEnabled = false
        bar_chart.setPinchZoom(false)
        bar_chart.isDoubleTapToZoomEnabled = false
        bar_chart.setDrawValueAboveBar(true)
        bar_chart.setDrawGridBackground(false)
        bar_chart.axisLeft.setDrawGridLines(false)
        bar_chart.xAxis.setDrawGridLines(false)
        bar_chart.xAxis.setDrawAxisLine(false)
        bar_chart.xAxis.position = XAxis.XAxisPosition.BOTH_SIDED

        val dates = arrayListOf<String>() // Dates
        dates.add("Пн")
        dates.add("Вт")
        dates.add("Ср")
        dates.add("Чт")
        dates.add("Пт")
        dates.add("Сб")
        dates.add("Вс")

        val xAxis = bar_chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f
        xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                if (value == value.toInt().toFloat())
                    return dates[value.toInt()]
                return ""
            }
        }

        val data = mutableListOf<Float>() // Data
        data.add(0f)
        data.add(10f)
        data.add(15f)
        data.add(20f)
        data.add(23f)
        data.add(29f)
        data.add(44f)

        val entry = mutableListOf<BarEntry>() // Bar entry
        entry.add(BarEntry(0f, data[0] + 5f, dates[0]))
        entry.add(BarEntry(1f, data[1] + 5f, dates[1]))
        entry.add(BarEntry(2f, data[2] + 5f, dates[2]))
        entry.add(BarEntry(3f, data[3] + 5f, dates[3]))
        entry.add(BarEntry(4f, data[4] + 5f, dates[4]))
        entry.add(BarEntry(5f, data[5] + 5f, dates[5]))
        entry.add(BarEntry(6f, data[6] + 5f, dates[6]))


        val barChartColorArray = mutableListOf<Int>()
        for (i in 0..6) {
            if (data[i] > 0f)
                barChartColorArray.add(Color.rgb(147, 112, 219)) // Purple
            else
                barChartColorArray.add(Color.rgb(169, 169, 169)) // Gray
        }

        val barDataSet = BarDataSet(entry, "")
        barDataSet.colors = barChartColorArray // Colors in RGB

        val barData = BarData(barDataSet)

        var roundedBarChartRenderer =
            RoundedBarChartRenderer(bar_chart, bar_chart.animator, bar_chart.viewPortHandler) // Custom renderer
        roundedBarChartRenderer.setmRadius(15f) // Corner Radius

        bar_chart.renderer = roundedBarChartRenderer

        barDataSet.valueTextSize = 12f
        bar_chart.data = barData
        bar_chart.invalidate()
    }
}
