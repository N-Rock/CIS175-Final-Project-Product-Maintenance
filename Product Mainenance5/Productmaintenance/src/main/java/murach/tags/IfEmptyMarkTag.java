package murach.tags;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;
import jakarta.servlet.jsp.JspWriter;
import java.io.IOException;

public class IfEmptyMarkTag extends TagSupport {
    private String field;
    

    // Setter for field attribute
    public void setField(String field) {
        
        this.field = field;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            // Check if the field is empty
            if (field == null || field.trim().isEmpty()) {
                out.print("*"); // Always print "*" if the field is empty
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        return SKIP_BODY; // Skip the body of the tag
    }
}

