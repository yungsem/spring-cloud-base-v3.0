package com.yungsem.baseauth.service.endpointconfig.exceptiontranslate;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.yungsem.basecommon.pojo.common.R;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;

/**
 * 对自定义的 BaseOAuth2Exception 进行自定义的序列化
 *
 * @author yangsen
 * @version 1.0
 * @since 2020-11-08
 */
public class BaseOAuth2ExceptionSerializer extends StdSerializer<BaseWebResponseExceptionTranslator.BaseOAuth2Exception> {
    protected BaseOAuth2ExceptionSerializer() {
        super(BaseWebResponseExceptionTranslator.BaseOAuth2Exception.class);
    }

    @Override
    public void serialize(BaseWebResponseExceptionTranslator.BaseOAuth2Exception e, JsonGenerator generator, SerializerProvider serializerProvider) throws IOException {
        generator.writeStartObject();
        generator.writeObjectField("code", R.CODE_FAIL);
        String message = e.getMessage();
        if (message != null) {
            message = HtmlUtils.htmlEscape(message);
        }
        generator.writeStringField("message", message);
        generator.writeObjectField("data", null);
        generator.writeEndObject();
    }
}
