package com.fliurkevych.pdp.pdpspringcore.xml;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * @author Oleh Fliurkevych
 */
@Slf4j
@Component
public class XmlService {

  public <T> T unmarshal(String xmlPath, Class<T> tClass) {
    try {
      log.info("Try to unmarshal [{}] objects", tClass.getName());
      JAXBContext jaxbContext = JAXBContext.newInstance(tClass);
      Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

      T objects = (T) jaxbUnmarshaller.unmarshal(new File(xmlPath));
      log.info("Unmarshal objects: {}", objects);

      return objects;
    } catch (JAXBException e) {
      e.printStackTrace();
      return null;
    }
  }

}
