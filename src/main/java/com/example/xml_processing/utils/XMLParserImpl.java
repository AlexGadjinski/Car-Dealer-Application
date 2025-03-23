package com.example.xml_processing.utils;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class XMLParserImpl implements XMLParser {
    @SuppressWarnings("unchecked")
    @Override
    public <E> E importFile(Class<E> clazz, String path) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (E) unmarshaller.unmarshal(new File(path));
        } catch (JAXBException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public <E> void exportFile(E e, String path) {
        try {
            JAXBContext context = JAXBContext.newInstance(e.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(e, new File(path));
        } catch (JAXBException exception) {
            exception.printStackTrace();
        }
    }
}
