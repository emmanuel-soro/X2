package ar.edu.soa.interfaces;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FormatoEjeX extends ValueFormatter {

    private long referenceTimestamp; // minimum timestamp in your data set
    private DateFormat mDataFormat;
    private Date mDate;

    public FormatoEjeX(long referenceTimestamp) {
        this.referenceTimestamp = referenceTimestamp;
        this.mDataFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.GERMANY);
        this.mDate = new Date();
    }

    /**
     * Called when a value from an axis is to be formatted
     * before being drawn. For performance reasons, avoid excessive calculations
     * and memory allocations inside this method.
     *
     * @param value the value to be formatted
     * @return
     */
    @Override
    public String getFormattedValue(float value) {
        // convertedTimestamp = originalTimestamp - referenceTimestamp
        System.out.println("formateo: "+value);
        long convertedTimestamp = (long) value;

        // Retrieve original timestamp
        long originalTimestamp = referenceTimestamp + convertedTimestamp;

        // Convert timestamp to hour:minute
        return getHour(originalTimestamp);
    }


    public int getDecimalDigits() {
        return 0;
    }

    private String getHour(long timestamp) {
        try {
            mDate.setTime(timestamp * 1000);
            return mDataFormat.format(mDate);
        } catch (Exception ex) {
            return "xx";
        }
    }


}
