/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ape.rgob.lminam.ecommon.scontrolador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("ValidaCorreo")
public class EmailValidator implements Validator{

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        
        Pattern patron = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = patron.matcher((CharSequence) o);
        HtmlInputText htmlInputText = (HtmlInputText) uic;
        String label;
        if (htmlInputText.getLabel() == null || htmlInputText.getLabel().trim().equals("")) {
            label=htmlInputText.getId();
        }
        else{
            label=htmlInputText.getLabel();
        }
        if(!matcher.matches()){
            FacesMessage facesMessage=
                    new FacesMessage(label +": no es una dirección de email válida");
            throw new ValidatorException(facesMessage);
        }

    }
    
}
