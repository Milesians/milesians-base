//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.milesians.provider.web.converter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.springframework.format.Formatter;

public class WebLocalDateFormatter implements Formatter<LocalDate> {

    private final DateTimeFormatter dtf;

    public WebLocalDateFormatter(final DateTimeFormatter dtf) {
        this.dtf = dtf;
    }

    @Override
    public LocalDate parse(final String s, final Locale locale) throws ParseException {
        return LocalDate.parse(s, dtf.withLocale(locale));
    }

    @Override
    public String print(final LocalDate date, final Locale locale) {
        return dtf.withLocale(locale).format(date);
    }

}
