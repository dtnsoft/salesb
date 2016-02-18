package com.offact.framework.util;

import java.net.*;
import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.InputSource;

import com.offact.framework.util.StringUtil;

public class XmlUtil
{

    private XmlUtil()
    {
    }

    public static Element loadDocument(URL url)
    {
        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource xmlSrc = new InputSource(url.openStream());
            Document doc = builder.parse(xmlSrc);
            Element root = doc.getDocumentElement();
            root.normalize();
            return root;
        }
        catch(SAXParseException err)
        {
            System.out.println("XmlUtil Parsing error, line " + err.getLineNumber() + ", uri " + err.getSystemId());
        }
        catch(SAXException e)
        {
            System.out.println("XmlUtil error: " + e);
        }
        catch(MalformedURLException mfx)
        {
            System.out.println("XmlUtil error: " + mfx);
        }
        catch(IOException e)
        {
            System.out.println("XmlUtil error: " + e);
        }
        catch(Exception pce)
        {
            pce.printStackTrace();
            System.out.println("XmlUtil error: " + pce);
        }
        return null;
    }
    
    public static Element loadDocument(String filename)
    {
        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            File xmlSrc = new File(filename);
            Document doc = builder.parse(xmlSrc);
            Element root = doc.getDocumentElement();
            root.normalize();
            return root;
        }
        catch(SAXParseException err)
        {
            System.out.println("XmlUtil Parsing error, line " + err.getLineNumber() + ", uri " + err.getSystemId());
        }
        catch(SAXException e)
        {
            System.out.println("XmlUtil error: " + e);
        }
        catch(MalformedURLException mfx)
        {
            System.out.println("XmlUtil error: " + mfx);
        }
        catch(IOException e)
        {
            System.out.println("XmlUtil error: " + e);
        }
        catch(Exception pce)
        {
            pce.printStackTrace();
            System.out.println("XmlUtil error: " + pce);
        }
        return null;
    }
    public static Element loadStringDocument(String resultXml)
    {
        try
        {
        	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc =builder.parse(new InputSource(new StringReader(resultXml)));
            
            Element root = doc.getDocumentElement();
            root.normalize();
            return root;
        }
        catch(SAXParseException err)
        {
            System.out.println("XmlUtil Parsing error, line " + err.getLineNumber() + ", uri " + err.getSystemId());
        }
        catch(SAXException e)
        {
            System.out.println("XmlUtil error: " + e);
        }
        catch(MalformedURLException mfx)
        {
            System.out.println("XmlUtil error: " + mfx);
        }
        catch(IOException e)
        {
            System.out.println("XmlUtil error: " + e);
        }
        catch(Exception pce)
        {
            pce.printStackTrace();
            System.out.println("XmlUtil error: " + pce);
        }
        return null;
    }

    public static String getTagValue(Element root, String tagName)
    {
        NodeList nList = root.getElementsByTagName(tagName);
        for(int i = 0; i < nList.getLength(); i++)
        {
            Node node = nList.item(i);
            if(node != null)
            {
                Node child = node.getFirstChild();
                if(child != null && child.getNodeValue() != null)
                    return child.getNodeValue();
            }
        }

        return "";
    }

    public static String getSubTagValue(Node node, String subTagName)
    {
        NodeList nList = node.getChildNodes();
        for(int i = 0; i < nList.getLength(); i++)
        {
            Node child = nList.item(i);
            if(child != null && child.getNodeName() != null && child.getNodeName().equals(subTagName))
            {
                Node grandChild = child.getFirstChild();
                if(grandChild != null && grandChild.getNodeValue() != null)
                    return grandChild.getNodeValue();
            }
        }

        return "";
    }

    public static String getSubTagValue(Element root, String tagName, String subTagName)
    {
        String returnString = "";
        NodeList list = root.getElementsByTagName(tagName);
        for(int loop = 0; loop < list.getLength(); loop++)
        {
            Node node = list.item(loop);
            if(node != null)
            {
                NodeList children = node.getChildNodes();
                for(int innerLoop = 0; innerLoop < children.getLength(); innerLoop++)
                {
                    Node child = children.item(innerLoop);
                    if(child != null && child.getNodeName() != null && child.getNodeName().equals(subTagName))
                    {
                        Node grandChild = child.getFirstChild();
                        if(grandChild != null && grandChild.getNodeValue() != null)
                            return grandChild.getNodeValue();
                    }
                }

            }
        }

        return returnString;
    }

    public static ArrayList getSubTagValues(Element root, String tagName, String subTagName)
    {
        ArrayList results = new ArrayList();
        NodeList list = root.getElementsByTagName(tagName);
        for(int loop = 0; loop < list.getLength(); loop++)
        {
            Node node = list.item(loop);
            if(node != null)
            {
                NodeList children = node.getChildNodes();
                for(int innerLoop = 0; innerLoop < children.getLength(); innerLoop++)
                {
                    Node child = children.item(innerLoop);
                    if(child != null && child.getNodeName() != null && child.getNodeName().equals(subTagName))
                    {
                        Node grandChild = child.getFirstChild();
                        if(grandChild != null && grandChild.getNodeValue() != null)
                            results.add(grandChild.getNodeValue());
                    }
                }

            }
        }

        return results;
    }

    public static String getSubTagAttribute(Element root, String tagName, String subTagName, String attribute)
    {
        String returnString = "";
        NodeList list = root.getElementsByTagName(tagName);
        for(int loop = 0; loop < list.getLength(); loop++)
        {
            Node node = list.item(loop);
            if(node != null)
            {
                NodeList children = node.getChildNodes();
                for(int innerLoop = 0; innerLoop < children.getLength(); innerLoop++)
                {
                    Node child = children.item(innerLoop);
                    if(child != null && child.getNodeName() != null && child.getNodeName().equals(subTagName) && (child instanceof Element))
                        return ((Element)child).getAttribute(attribute);
                }

            }
        }

        return returnString;
    }

    public static String getAttribute(Node node, String attrName)
    {
        if(node != null && (node instanceof Element))
            return ((Element)node).getAttribute(attrName);
        else
            return "";
    }
}
