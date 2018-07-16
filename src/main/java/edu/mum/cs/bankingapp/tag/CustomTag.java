package edu.mum.cs.bankingapp.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class CustomTag extends SimpleTagSupport {
    private String type, message;

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        if (type != null && "error".equals(type)) {
            out.write(String.format("<span style='color:red;font-family:arial;font-size: 14px;'>%s</span>",
                    message));
        } else if(type != null && "success".equals(type)) {
            out.write(String.format("<span style='color:green;font-family:arial;font-size: 14px;'>%s</span>",
                    message));
        }else{
            out.write(String.format("<span>%s</span>", message));
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
