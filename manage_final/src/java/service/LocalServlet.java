package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LocalDAO;
import dao.PplaceDAO;
import model.Gallery;
import model.Local;
import model.Pplace;
import dao.GalleryDAO;
import model.Contact;
import dao.ContactDAO;

/**
 * ControllerServlet.java This servlet acts as a page controller for the
 * application, handling all requests from the user.
 *
 * @email Ramesh Fadatare
 */
@WebServlet("/")
public class LocalServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private LocalDAO localDAO;
    private PplaceDAO pplaceDAO;
    private GalleryDAO galleryDAO;
    private ContactDAO contacDAO;

    @Override
    public void init() {
        localDAO = new LocalDAO();
        pplaceDAO = new PplaceDAO();
        galleryDAO = new GalleryDAO();
        contacDAO = new ContactDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertUser(request, response);
                    break;
                case "/delete":
                    deleteUser(request, response);
                    break;

                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateUser(request, response);
                    break;

                //place
                case "/new_place":
                    showNewFormPlace(request, response);
                    break;

                case "/insert_place":
                    insertPplace(request, response);
                    break;
                case "/delete_place":
                    deletePplace(request, response);
                    break;
                case "/edit_place":
                    showEditFormPlace(request, response);
                    break;
                case "/update_place":
                    updatePplace(request, response);
                    break;
                case "/place":
                    listPplace(request, response);
                    break;
                //place 

                //gallery
                case "/news":
                    showNewFormGallery(request, response);
                    break;
                case "/insert_gallery":
                    insertGallery(request, response);
                    break;
                case "/delete_gallery":
                    deleteGallery(request, response);
                    break;
                case "/edit_gallery":
                    showEditFormGallery(request, response);
                    break;
                case "/update_gallery":
                    updateGallery(request, response);
                    break;
                case "/ds":
                    listGallery(request, response);
                    break;
                //gallery

                //contact
                case "/contact":
                    listContact(request, response);
                    break;
                //contact

                default:
                    listUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    //contact

    public void listContact(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Contact> listContact = contacDAO.selectAllContact();
        request.setAttribute("listContact", listContact);
        RequestDispatcher dispatcher = request.getRequestDispatcher("contact-list.jsp");
        dispatcher.forward(request, response);
    }

    //contact
    //place
    public void listPplace(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Pplace> listPplaces = pplaceDAO.selectAllPplace();
        request.setAttribute("listPplace", listPplaces);
        RequestDispatcher dispatcher = request.getRequestDispatcher("pplace-ds.jsp");
        dispatcher.forward(request, response);
    }

    public static void showNewFormPlace(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("pplace-model.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditFormPlace(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Pplace existingUser = pplaceDAO.selectPplace(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("pplace-model.jsp");
        request.setAttribute("pplace", existingUser);
        dispatcher.forward(request, response);

    }

    private void insertPplace(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String image = request.getParameter("image");
        String status = request.getParameter("status");
        Pplace newPplace = new Pplace(name, image, status);
        pplaceDAO.insertPplace(newPplace);
        response.sendRedirect("place");
    }

    private void updatePplace(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String image = request.getParameter("image");
        String status = request.getParameter("status");

        Pplace book = new Pplace(id, name, image, status);
        pplaceDAO.updatePplace(book);
        response.sendRedirect("place");
    }

    private void deletePplace(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        pplaceDAO.deletePplace(id);
        response.sendRedirect("place");

    }
//place

    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Local> listLocal = localDAO.selectAllLocal();
        request.setAttribute("listUser", listLocal);
        RequestDispatcher dispatcher = request.getRequestDispatcher("local-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("local-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Local existingUser = localDAO.selectLocal(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("local-form.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);

    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String image = request.getParameter("image");
        String country = request.getParameter("country");
        Local newUser = new Local(name, image, country);
        localDAO.insertLocal(newUser);
        response.sendRedirect("list");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String image = request.getParameter("image");
        String country = request.getParameter("country");

        Local book = new Local(id, name, image, country);
        localDAO.updateLocal(book);
        response.sendRedirect("list");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        localDAO.deleteLocal(id);
        response.sendRedirect("list");

    }

    //gallery
    private void listGallery(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Gallery> listGallery = galleryDAO.selectAllGallery();
        request.setAttribute("listGallery", listGallery);
        RequestDispatcher dispatcher = request.getRequestDispatcher("gallery-ds.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewFormGallery(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("gallery-model.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditFormGallery(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Gallery existingGallery = galleryDAO.selectGallery(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("gallery-model.jsp");
        request.setAttribute("gallery", existingGallery);
        dispatcher.forward(request, response);

    }

    private void insertGallery(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String image = request.getParameter("image");
        String country = request.getParameter("country");
        Gallery newGallery = new Gallery(name, image, country);
        galleryDAO.insertGallery(newGallery);
        response.sendRedirect("ds");
    }

    private void updateGallery(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String image = request.getParameter("image");
        String country = request.getParameter("country");

        Gallery book = new Gallery(id, name, image, country);
        galleryDAO.updateGallery(book);
        response.sendRedirect("ds");
    }

    private void deleteGallery(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        galleryDAO.deleteGallery(id);
        response.sendRedirect("ds");

    }
    //gallery

}
