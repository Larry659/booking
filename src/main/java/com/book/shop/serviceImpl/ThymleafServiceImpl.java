package com.book.shop.serviceImpl;

import com.book.shop.service.ThymleafService;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.Map;
@Service
public class ThymleafServiceImpl implements ThymleafService {
    private static final String MAIL_TEMPLATE_BASE_NAME = "mail/mailMessages";
    private static final String MAIL_TEMPLATE_PREFIX = "/templates/";
    private static final String MAIL_TEMPLATE_SUFFIX = ".html";
    private static final String UTF_8 = "UTF-8";
    private static TemplateEngine templateEngine;
    static{
        templateEngine = emailTemplateEngine();
    }

    private static TemplateEngine emailTemplateEngine() {
       final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
       templateEngine.setTemplateResolver(htmlTemplateResolver());
       templateEngine.setTemplateEngineMessageSource(emailMessageSource());
       return templateEngine;
    }

    private static ITemplateResolver htmlTemplateResolver() {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(MAIL_TEMPLATE_SUFFIX);
        templateResolver.setPrefix(MAIL_TEMPLATE_PREFIX);
        templateResolver.setCacheable(false);
        templateResolver.setCharacterEncoding(UTF_8);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        return templateResolver;
    }

    private static ResourceBundleMessageSource emailMessageSource() {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames(MAIL_TEMPLATE_BASE_NAME);
        return messageSource;
    }

    @Override
    public String createContent(String template, Map<String, Object> variables) {
        final Context context = new Context();
        context.setVariables(variables);
        return templateEngine.process(template,context);
    }
}
