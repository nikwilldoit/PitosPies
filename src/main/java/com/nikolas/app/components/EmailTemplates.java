package com.nikolas.app.components;

import com.nikolas.app.controllers.forms.FormDataContact;
import com.nikolas.app.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class EmailTemplates {

    @Autowired
    private MailService mailService;

    public void sendEmailToClientContactForm(FormDataContact formData) {
        String text =
                "Παραλάβαμε το μήνυμα σας με τα εξής στοιχεία: \n" +
                        "\tΟνοματεπώνυμο: " + formData.getFullname() + "\n" +
                        "\tE-mail: " + formData.getEmail() + "\n" +
                        "\tΤηλέφωνο: " + formData.getTel() + "\n" +
                        "\tΜήνυμα: " + formData.getMessage() + "\n\n" +
                        "και θα επικοινωνήσουμε μαζί σας σύντομα!";

        mailService.sendTextEmail(formData.getEmail(), "Ενημέρωση Επικοινωνίας", text);
    }
    public void sendEmailToAdminContactForm(FormDataContact formData) throws IOException {
        ClassPathResource resource = new ClassPathResource("project.properties");
        Properties properties = PropertiesLoaderUtils.loadProperties(resource);
        String adminEmail = properties.getProperty("mail.admin");

        String text =
                "Νέο μήνυμα από τη φόρμα επικοινωνίας της σελίδας μας: \n" +
                        "\tΟνοματεπώνυμο: " + formData.getFullname() + "\n" +
                        "\tE-mail: " + formData.getEmail() + "\n" +
                        "\tΤηλέφωνο: " + formData.getTel() + "\n" +
                        "\tΜήνυμα: " + formData.getMessage() + "\n";

        mailService.sendTextEmail(adminEmail, "Εισερχόμενο Μήνυμα από τη φόρμα επικοινωνίας", text);
    }
}