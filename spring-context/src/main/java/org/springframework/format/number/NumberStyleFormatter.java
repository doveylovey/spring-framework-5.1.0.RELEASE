/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.format.number;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import org.springframework.lang.Nullable;

/**
 * A general-purpose number formatter using NumberFormat's number style.
 *
 * <p>Delegates to {@link java.text.NumberFormat#getInstance(Locale)}.
 * Configures BigDecimal parsing so there is no loss in precision.
 * Allows configuration over the decimal number pattern.
 * The {@link #parse(String, Locale)} routine always returns a BigDecimal.
 *
 * @author Keith Donald
 * @author Juergen Hoeller
 * @see #setPattern
 * @see #setLenient
 * @since 4.2
 */
public class NumberStyleFormatter extends AbstractNumberFormatter {

    @Nullable
    private String pattern;


    /**
     * Create a new NumberStyleFormatter without a pattern.
     */
    public NumberStyleFormatter() {
    }

    /**
     * Create a new NumberStyleFormatter with the specified pattern.
     *
     * @param pattern the format pattern
     * @see #setPattern
     */
    public NumberStyleFormatter(String pattern) {
        this.pattern = pattern;
    }


    /**
     * Specify the pattern to use to format number values.
     * If not specified, the default DecimalFormat pattern is used.
     *
     * @see java.text.DecimalFormat#applyPattern(String)
     */
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }


    @Override
    public NumberFormat getNumberFormat(Locale locale) {
        NumberFormat format = NumberFormat.getInstance(locale);
        if (!(format instanceof DecimalFormat)) {
            if (this.pattern != null) {
                throw new IllegalStateException("Cannot support pattern for non-DecimalFormat: " + format);
            }
            return format;
        }
        DecimalFormat decimalFormat = (DecimalFormat) format;
        decimalFormat.setParseBigDecimal(true);
        if (this.pattern != null) {
            decimalFormat.applyPattern(this.pattern);
        }
        return decimalFormat;
    }

}
