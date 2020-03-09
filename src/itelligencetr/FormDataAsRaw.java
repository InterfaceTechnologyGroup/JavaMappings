package itelligencetr;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import com.sap.aii.mapping.api.AbstractTransformation;
import com.sap.aii.mapping.api.StreamTransformationException;
import com.sap.aii.mapping.api.TransformationInput;
import com.sap.aii.mapping.api.TransformationOutput;

public class FormDataAsRaw extends AbstractTransformation {
	@Override
	public void transform(TransformationInput inputData, TransformationOutput outputData)
			throws StreamTransformationException {
		// TODO Auto-generated method stub
		// Transforming input and output as InputStream and OutputStream to be
		// able to create Documents
		InputStream inputDataStream = inputData.getInputPayload().getInputStream();
		OutputStream outputDataStream = outputData.getOutputPayload().getOutputStream();

		try {

			// Creating document from inputDataStream
			Document document = DocumentBuilderFactoryImpl.newInstance().newDocumentBuilder().parse(inputDataStream);
			String outputString = "uid=" + document.getElementsByTagName("username").item(0).getTextContent() + "&pwd="
					+ document.getElementsByTagName("password").item(0).getTextContent() + "&BeginDate="
					+ document.getElementsByTagName("BeginDate").item(0).getTextContent() + "&EndDate="
					+ document.getElementsByTagName("EndDate").item(0).getTextContent();

			/*
			 * DocumentBuilderFactory documentBuilderFactory =
			 * DocumentBuilderFactoryImpl.newInstance().newDocumentBuilder().
			 * parse(inputDataStream); DocumentBuilder documentBuilder =
			 * documentBuilderFactory.newDocumentBuilder(); Document document
			 * =C:\Users\tr30113\Desktop\Dev\Exported_Projects\formdata.jar
			 * documentBuilder.parse(inputDataStream); //
			 * uid=U00746007B&pwd=8I44j8pf6a // Getting NodeList named "C_C041"
			 * String outputString = "uid=" +
			 * document.getElementsByTagName("username").item(0).getTextContent(
			 * ) + "&pwd=" +
			 * document.getElementsByTagName("password").item(0).getTextContent(
			 * );
			 * 
			 * DocumentBuilderFactory factory =
			 * DocumentBuilderFactoryImpl.newInstance(); DocumentBuilder builder
			 * = factory.newDocumentBuilder(); ByteArrayInputStream input = new
			 * ByteArrayInputStream(outputString.getBytes("UTF-8"));
			 * 
			 * Document doc = builder.parse(input);
			 * 
			 */
			outputDataStream.write(outputString.getBytes());
			outputDataStream.flush();
			outputDataStream.close();

			// Giving document as output
			// TransformerFactory.newInstance().newTransformer().transform(new
			// DOMSource(document),new StreamResult(outputDataStream));

		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
