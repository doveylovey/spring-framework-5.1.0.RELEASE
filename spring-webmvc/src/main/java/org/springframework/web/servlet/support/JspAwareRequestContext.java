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

package org.springframework.web.servlet.support;

import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.jstl.core.Config;

import org.springframework.lang.Nullable;

/**
 * JSP-aware (and JSTL-aware) subclass of RequestContext, allowing for
 * population of the context from a {@code javax.servlet.jsp.PageContext}.
 *
 * <p>This context will detect a JSTL locale attribute in page/request/session/application
 * scope, in addition to the fallback locale strategy provided by the base class.
 *
 * @author Juergen Hoeller
 * @see #getFallbackLocale
 * @since 1.1.4
 */
public class JspAwareRequestContext extends RequestContext {

    private PageContext pageContext;


    /**
     * Create a new JspAwareRequestContext for the given page context,
     * using the request attributes for Errors retrieval.
     *
     * @param pageContext current JSP page context
     */
    public JspAwareRequestContext(PageContext pageContext) {
        this(pageContext, null);
    }

    /**
     * Create a new JspAwareRequestContext for the given page context,
     * using the given model attributes for Errors retrieval.
     *
     * @param pageContext current JSP page context
     * @param model       the model attributes for the current view
     *                    (can be {@code null}, using the request attributes for Errors retrieval)
     */
    public JspAwareRequestContext(PageContext pageContext, @Nullable Map<String, Object> model) {
        super((HttpServletRequest) pageContext.getRequest(), (HttpServletResponse) pageContext.getResponse(),
                pageContext.getServletContext(), model);
        this.pageContext = pageContext;
    }


    /**
     * Return the underlying PageContext.
     * Only intended for cooperating classes in this package.
     */
    protected final PageContext getPageContext() {
        return this.pageContext;
    }

    /**
     * This implementation checks for a JSTL locale attribute in page,
     * request, session or application scope; if not found, returns the
     * {@code HttpServletRequest.getLocale()}.
     */
    @Override
    protected Locale getFallbackLocale() {
        if (jstlPresent) {
            Locale locale = JstlPageLocaleResolver.getJstlLocale(getPageContext());
            if (locale != null) {
                return locale;
            }
        }
        return getRequest().getLocale();
    }

    /**
     * This implementation checks for a JSTL time zone attribute in page,
     * request, session or application scope; if not found, returns {@code null}.
     */
    @Override
    protected TimeZone getFallbackTimeZone() {
        if (jstlPresent) {
            TimeZone timeZone = JstlPageLocaleResolver.getJstlTimeZone(getPageContext());
            if (timeZone != null) {
                return timeZone;
            }
        }
        return null;
    }


    /**
     * Inner class that isolates the JSTL dependency.
     * Just called to resolve the fallback locale if the JSTL API is present.
     */
    private static class JstlPageLocaleResolver {

        @Nullable
        public static Locale getJstlLocale(PageContext pageContext) {
            Object localeObject = Config.find(pageContext, Config.FMT_LOCALE);
            return (localeObject instanceof Locale ? (Locale) localeObject : null);
        }

        @Nullable
        public static TimeZone getJstlTimeZone(PageContext pageContext) {
            Object timeZoneObject = Config.find(pageContext, Config.FMT_TIME_ZONE);
            return (timeZoneObject instanceof TimeZone ? (TimeZone) timeZoneObject : null);
        }
    }

}
