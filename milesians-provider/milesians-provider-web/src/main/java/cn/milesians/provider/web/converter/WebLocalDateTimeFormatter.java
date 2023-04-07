//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.milesians.provider.web.converter;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.springframework.format.Formatter;

public class WebLocalDateTimeFormatter implements Formatter<LocalDateTime> {

    private final DateTimeFormatter dtf;

    public WebLocalDateTimeFormatter(final DateTimeFormatter dtf) {
        this.dtf = dtf;
    }

    @Override
    public LocalDateTime parse(final String s, final Locale locale) throws ParseException {
        return LocalDateTime.parse(s, dtf.withLocale(locale));
    }

    @Override
    public String print(final LocalDateTime date, final Locale locale) {
        return dtf.withLocale(locale).format(date);
    }

}
