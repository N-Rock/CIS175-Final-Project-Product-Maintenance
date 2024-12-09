
package murach.tags;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;
import java.io.IOException;


public class FlagTag extends TagSupport {
    private boolean hasEmptyField;

    public void setHasEmptyField(boolean hasEmptyField) {
        this.hasEmptyField = hasEmptyField;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            if (hasEmptyField) {
                pageContext.getOut().print("<p style='color:red;'>Please fill in all required fields.</p>");
            }
        } catch (IOException e) {
            throw new JspException("Error in FlagTag tag", e);
        }
        return SKIP_BODY;
    }
}
