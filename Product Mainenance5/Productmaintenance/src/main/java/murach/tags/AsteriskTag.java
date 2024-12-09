
package murach.tags;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;
import java.io.IOException;



public class AsteriskTag extends TagSupport {
    private String fieldValue;

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            if (fieldValue == null || fieldValue.trim().isEmpty()) {
                out.print("*");
            }
        } catch (IOException e) {
            throw new JspException("Error in AsteriskTag", e);
        }
        return SKIP_BODY;
    }
}