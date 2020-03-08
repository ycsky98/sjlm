package cn.sjlm.sjlmapi.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
 
public class XMLUtil {
 
    public static Map<String, String> reqParamsToMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        SAXReader reader = new SAXReader();
        InputStream in = null;
        try {
            in = request.getInputStream();
            Document doc = reader.read(in);
            Element root = doc.getRootElement();
            List<Element> list = root.elements();
            for (Element element : list) {
                map.put(element.getName(), element.getText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
 
 
    public static String get(String attr, String xmlfilepath) {
        SAXReader reader = new SAXReader();
        FileInputStream in = null;
        try {
 
            File xmlfile = new File(xmlfilepath);
            in = new FileInputStream(xmlfile);
            Document doc = reader.read(in);
            Element root = doc.getRootElement();
            Node node = doc.selectSingleNode(attr);
            //[@name='ReportServiceImpl.findNormal']
            //	System.out.println(node.getText());
            return node.getText();
        } catch (Exception ex) {
            return "";
        }
    }
 
    public static String getNodeByPath(String path, String xml) {
        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read(new ByteArrayInputStream(xml.getBytes("utf-8")));
            Node node = doc.selectSingleNode(path);
            return node.getText();
        } catch (UnsupportedEncodingException ex) {
            return "";
        } catch (DocumentException ex) {
            return "";
        }
    }
 
    public static Map<String, String> getNodeByPaths(String xml, String... path) {
        Map<String, String> nodes = new HashMap<>();
        List<String> paths = path != null ? Arrays.asList(path) : null;
        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read(new ByteArrayInputStream(xml.getBytes("utf-8")));
            for (String p : paths) {
                Node node = doc.selectSingleNode(p);
                if (node != null) {
                    nodes.put(p, node.getText());
                }
            }
            return nodes;
        } catch (Exception ex) {
            return null;
        }
    }
 
    /**
     * 将字符串xml转换成Map
     *
     * @param xml
     * @return Map<String,String>
     */
    public static Map<String, String> xmlToMap(String xml) {
        if (StringUtils.isEmpty(xml)) {
            throw new RuntimeException("解析微信请求返回的xml为空");
        }
        Map<String, String> map = new TreeMap<String, String>();
        try {
            // 将传入的字符串xml转换成Document对象
            Document doc = DocumentHelper.parseText(xml);
            // 获取根节点
            Element root = doc.getRootElement();
            // 获取所有的子节点
            @SuppressWarnings("rawtypes")
            List list = root.elements();
            // 遍历添加到map中
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    Element child = (Element) list.get(i);
                    map.put(child.getName(), child.getTextTrim());
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }
 
    /**
     * 创建请求的xml文件
     *
     * @param parameterMap
     * @return
     * @throws Exception String
     */
    public static String createRequestXml(Map<String, String> parameterMap) throws Exception {
        Document document = DocumentHelper.createDocument();
        document.setXMLEncoding("utf-8");
        // 创建根节点
        Element xmlRoot = document.addElement("xml");
        // 转换成TreeMap
        Set<Map.Entry<String, String>> paramterSet = parameterMap.entrySet();
        Iterator<Map.Entry<String, String>> parameterMapIterator = paramterSet.iterator();
 
        Map<String, String> treeMap = new TreeMap<String, String>();
 
        while (parameterMapIterator.hasNext()) {
            Map.Entry<String, String> param = parameterMapIterator.next();
            if (StringUtils.isEmpty(param.getKey())) {
                throw new RuntimeException("微信支付,转换xml参数为空");
            }
            treeMap.put(param.getKey(), param.getValue());
        }
 
        Set<Map.Entry<String, String>> treeMapSet = treeMap.entrySet();
        Iterator<Map.Entry<String, String>> treeMapSetIterator = treeMapSet.iterator();
        while (treeMapSetIterator.hasNext()) {
            Map.Entry<String, String> param = treeMapSetIterator.next();
            // 如有空值就不参加校验
            if (StringUtils.isEmpty(param.getKey()) || StringUtils.isEmpty(param.getValue())) {
                continue;
            }
            if (StringUtils.isNotEmpty(param.getKey())) {
                Element element = xmlRoot.addElement(param.getKey());
 
                if (StringUtils.isNotEmpty(param.getValue())) {
                    element.addText("<![CDATA[" + param.getValue() + "]]>");
                }
            }
        }
        String xmlString = formatXml(document, "utf-8", false);
        return xmlString;
    }
 
    /**
     * 格式化XML文档
     *
     * @param document xml文档
     * @param charset  字符串的编码
     * @param istrans  是否对属性和元素值进行转移
     * @return 格式化后XML字符串
     */
    private static String formatXml(Document document, String charset, boolean istrans) {
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding(charset);
        StringWriter sw = new StringWriter();
        XMLWriter xw = new XMLWriter(sw, format);
        xw.setEscapeText(istrans);
        try {
            xw.write(document);
            xw.flush();
            xw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }
}