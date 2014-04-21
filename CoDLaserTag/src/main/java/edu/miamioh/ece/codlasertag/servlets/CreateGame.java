/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.miamioh.ece.codlasertag.servlets;

import edu.miamioh.ece.codlasertag.game.Game;
import edu.miamioh.ece.codlasertag.game.GameServer;
import edu.miamioh.ece.codlasertag.game.gametypes.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kylerogers
 */
@WebServlet(name = "CreateGame", urlPatterns = {"/CreateGame"})
public class CreateGame extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("playgame.jsp");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String gameName = request.getParameter("gamename"); 
        String gameType = request.getParameter("gametype");
        int gameId = -1;

        Game gameToBeAdded;

        if (gameType.equals("humansvszombies"))    {
            gameToBeAdded = new HumansVsZombiesGame(gameName);
        }
        else if (gameType.equals("freeroam"))   {
            gameToBeAdded = new FreeRoamGame(gameName);
        }
        else    {
            return;
        }

        gameId = GameServer.addGame(gameToBeAdded);
        response.sendRedirect("playgame.jsp?gameId=" + gameId);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
