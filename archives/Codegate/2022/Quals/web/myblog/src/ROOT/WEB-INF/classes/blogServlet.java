
// Source code is decompiled from a .class file using FernFlower decompiler.
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class blogServlet extends HttpServlet {
    private String tmpDir = System.getProperty("java.io.tmpdir") + "/db/";

    public blogServlet() {
    }

    private boolean isLogin(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Object id = session.getAttribute("id");
        return id != null;
    }

    private boolean idCheck(String str) {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9_]");
        Matcher matcher = pattern.matcher(str);
        return !matcher.find() && str.length() <= 10;
    }

    private String decBase64(String str) {
        byte[] decodedBytes = Base64.getDecoder().decode(str);
        String decodedString = new String(decodedBytes);
        return decodedString;
    }

    private String encBase64(String str) {
        Base64.Encoder encoder = Base64.getEncoder();

        try {
            String encodedString = new String(encoder.encode(str.getBytes("utf-8")));
            return encodedString;
        } catch (Exception var4) {
            return "";
        }
    }

    private String encMD5(String str) {
        String MD5 = "";

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] byteData = md.digest();
            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < byteData.length; ++i) {
                sb.append(Integer.toString((byteData[i] & 255) + 256, 16).substring(1));
            }

            MD5 = sb.toString();
        } catch (NoSuchAlgorithmException var7) {
            System.out.println(var7.getMessage());
            MD5 = "";
        }

        return MD5;
    }

    private String lookupPage(String uri) {
        String[] array = uri.split("\\/");
        return array.length != 3 ? "error" : array[2].trim();
    }

    private boolean doRegister(HttpServletRequest req) {
        this.initUserDB();
        File userDB = new File(this.tmpDir, "users.xml");
        String id = req.getParameter("id");
        String pw = req.getParameter("pw");
        if (id != null && pw != null && this.idCheck(id)) {
            try {
                InputSource is = new InputSource(new FileInputStream(userDB));
                Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
                document.setXmlStandalone(true);
                NodeList usersNodeList = document.getElementsByTagName("users");
                Element userElement = document.createElement("user");
                userElement.setTextContent(id + "/" + this.encMD5(pw));
                usersNodeList.item(0).appendChild(userElement);
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
                transformer.setOutputProperty("encoding", "UTF-8");
                transformer.setOutputProperty("indent", "yes");
                DOMSource source = new DOMSource(document);
                StreamResult result = new StreamResult(new FileOutputStream(userDB));
                transformer.transform(source, result);
                return true;
            } catch (Exception var13) {
                System.out.println(var13.getMessage());
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean doLogin(HttpServletRequest req) {
        this.initUserDB();
        String id = req.getParameter("id");
        String pw = req.getParameter("pw");
        if (id != null && pw != null) {
            id = id.trim();
            pw = this.encMD5(pw.trim());
            Boolean flag = false;

            try {
                File userDB = new File(this.tmpDir, "users.xml");
                InputSource is = new InputSource(new FileInputStream(userDB));
                Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
                NodeList userList = document.getElementsByTagName("user");
                int length = userList.getLength();

                for (int i = 0; i < length; ++i) {
                    Node user = userList.item(i);
                    String info = user.getTextContent();
                    if (info.trim().equals(id + "/" + pw)) {
                        flag = true;
                        req.getSession().setAttribute("id", id);
                        this.initUserArticle(req);
                        break;
                    }
                }

                return flag;
            } catch (Exception var13) {
                System.out.println(var13.getMessage());
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean doWriteArticle(HttpServletRequest req) {
        this.initUserArticle(req);
        String id = (String) req.getSession().getAttribute("id");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        if (id != null && title != null && content != null) {
            title = this.encBase64(title);
            content = this.encBase64(content);
            File userArticle = new File(this.tmpDir + "/article/", id + ".xml");

            try {
                InputSource is = new InputSource(new FileInputStream(userArticle));
                Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
                document.setXmlStandalone(true);
                NodeList articleNodeList = document.getElementsByTagName("articles");
                int length = document.getElementsByTagName("article").getLength();
                Element articleElement = document.createElement("article");
                articleElement.setAttribute("idx", Integer.toString(length + 1));
                Element titleElement = document.createElement("title");
                titleElement.setTextContent(title);
                Element contentElement = document.createElement("content");
                contentElement.setTextContent(content);
                articleElement.appendChild(titleElement);
                articleElement.appendChild(contentElement);
                articleNodeList.item(0).appendChild(articleElement);
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
                transformer.setOutputProperty("encoding", "UTF-8");
                transformer.setOutputProperty("indent", "yes");
                DOMSource source = new DOMSource(document);
                StreamResult result = new StreamResult(new FileOutputStream(userArticle));
                transformer.transform(source, result);
                return true;
            } catch (Exception var17) {
                System.out.println(var17.getMessage());
                return false;
            }
        } else {
            return false;
        }
    }

    private String[] doReadArticle(HttpServletRequest req) {
        String id = (String) req.getSession().getAttribute("id");
        String idx = req.getParameter("idx");
        if (!"null".equals(id) && idx != null) {
            File userArticle = new File(this.tmpDir + "/article/", id + ".xml");

            try {
                InputSource is = new InputSource(new FileInputStream(userArticle));
                Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
                XPath xpath = XPathFactory.newInstance().newXPath();
                String title = (String) xpath.evaluate("//article[@idx='" + idx + "']/title/text()", document,
                        XPathConstants.STRING);
                String content = (String) xpath.evaluate("//article[@idx='" + idx + "']/content/text()", document,
                        XPathConstants.STRING);
                title = this.decBase64(title.trim());
                content = this.decBase64(content.trim());
                return new String[] { title, content };
            } catch (Exception var10) {
                System.out.println(var10.getMessage());
                return null;
            }
        } else {
            return null;
        }
    }

    private void initUserArticle(HttpServletRequest req) {
        HttpSession session = req.getSession();
        String id = (String) session.getAttribute("id");
        if (!"null".equals(id)) {
            try {
                File articleDir = new File(this.tmpDir, "article");
                if (!articleDir.exists()) {
                    articleDir.mkdir();
                }

                File userArticle = new File(articleDir, id + ".xml");
                if (!userArticle.exists()) {
                    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                    Document doc = docBuilder.newDocument();
                    doc.setXmlStandalone(true);
                    Element articles = doc.createElement("articles");
                    doc.appendChild(articles);
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
                    transformer.setOutputProperty("encoding", "UTF-8");
                    transformer.setOutputProperty("indent", "yes");
                    DOMSource source = new DOMSource(doc);
                    StreamResult result = new StreamResult(new FileOutputStream(userArticle));
                    transformer.transform(source, result);
                }
            } catch (Exception var14) {
                System.out.println(var14.getMessage());
            }

        }
    }

    private void initUserDB() {
        File userDB = new File(this.tmpDir, "users.xml");

        try {
            if (!userDB.exists()) {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.newDocument();
                doc.setXmlStandalone(true);
                Element users = doc.createElement("users");
                doc.appendChild(users);
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
                transformer.setOutputProperty("encoding", "UTF-8");
                transformer.setOutputProperty("indent", "yes");
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new FileOutputStream(userDB));
                transformer.transform(source, result);
            }
        } catch (Exception var10) {
            System.out.println(var10.getMessage());
        }

    }

    private void alert(HttpServletRequest req, HttpServletResponse res, String msg, String back)
            throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        pw.println("<script>");
        pw.println("alert('" + msg + "')");
        if (back != null && back.length() > 0) {
            pw.print(";location.href='" + back + "';");
        }

        pw.println("</script>");
        pw.close();
    }

    public void init(ServletConfig config) {
        try {
            File dbDir = new File(this.tmpDir);
            if (!dbDir.exists()) {
                dbDir.mkdir();
            }

            this.initUserDB();
        } catch (Exception var3) {
            System.out.println(var3.getMessage());
        }

    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String page = this.lookupPage(req.getRequestURI()).trim();
        if (!"login".equals(page) && !"register".equals(page) && !this.isLogin(req)) {
            this.alert(req, res, "login first", "/blog/login");
        }

        if ("read".equals(page)) {
            req.setAttribute("article", this.doReadArticle(req));
        }

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/" + page + ".jsp");
        rd.forward(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String page = this.lookupPage(req.getRequestURI());
        if (!"login".equals(page) && !"register".equals(page) && !this.isLogin(req)) {
            this.alert(req, res, "login first", "/blog/login");
        }

        switch (page) {
            case "register":
                if (this.doRegister(req)) {
                    this.alert(req, res, "register ok", "/blog/write");
                } else {
                    this.alert(req, res, "register fail", "/blog/register");
                }
                break;
            case "login":
                if (this.doLogin(req)) {
                    this.alert(req, res, "login ok", "/blog/write");
                } else {
                    this.alert(req, res, "login fail", "/blog/login");
                }
                break;
            case "write":
                if (this.doWriteArticle(req)) {
                    this.alert(req, res, "write ok", "/");
                } else {
                    this.alert(req, res, "write fail", "/");
                }
        }

    }
}
