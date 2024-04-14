package balletApp.balletOK.gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBase {


    // VARIABLES

    // Variable de connexió a la BBDD
    Connection c;

    // Variable de consulta
    Statement query;

    // Dades de connexió (user, password, nom de la base de dades)
    String user, password, databaseName;

    // Estat de la connexió
    boolean connectat = false;



    // SETTER
    public DataBase(String user, String password, String databaseName){
        this.user = user;
        this.password = password;
        this.databaseName = databaseName;
    }



    // CONECTAR AMB DB
    public void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+databaseName+"?useTimezone=true&serverTimezone=UTC", user, password);
            query = c.createStatement();
            System.out.println("Connectat a la BBDD! :) ");
            connectat = true;
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }



    // GETTERS

    // Retorna el número de files d'una taula
    public int getNumRowsTaula(String nomTaula){
        try {
            ResultSet rs = query.executeQuery( "SELECT COUNT(*) AS n FROM "+ nomTaula );
            rs.next();
            int numRows = rs.getInt("n");
            return numRows;
        }
        catch(Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    public int getNumRowsQuery(String q){
        try {
            ResultSet rs = query.executeQuery( q);
            rs.next();
            return rs.getInt("n");
        }
        catch(Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    // Retorna el número de columnes d'una taula de la base de dades
    public int getNumColsTaula(String nomTaula){
        try {
            String q = "SELECT count(*) as n FROM information_schema.columns WHERE table_name ='"+ nomTaula +"' AND table_schema='"+databaseName+"'";
            System.out.println(q);
            ResultSet rs = query.executeQuery( q);
            rs.next();
            int numCols = rs.getInt("n");
            return numCols;
        }
        catch(Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    // Retorna les dades d'una taula en concret
    public String[][] getInfoTaulaLlista(String nomUsuario){
        int numFiles = getNumRowsTaula("lista");
        int numCols  = 3;
        String[][] info = new String[numFiles][numCols];
        try {
            ResultSet rs = query.executeQuery( "SELECT DISTINCT l.numCanciones AS NUM, l.título AS TITULO, c.nombre AS CATEGORIA FROM lista l, usuario u, categoría c WHERE l.Usuario = '"+nomUsuario+"' AND l.Categoría = c.idCategoría ORDER BY l.orden ASC");
            int nr = 0;
            while (rs.next()) {
                info[nr][0] = String.valueOf(rs.getString("NUM"));
                info[nr][1] = rs.getString("TITULO");
                info[nr][2] = rs.getString("CATEGORIA");
                nr++;
            }
            return info;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    // Retorna les dades d'una taula en concret
    public String[] getNombresListas(String nomUsuario){
        String qn = "SELECT COUNT(*) AS n FROM lista l, usuario u WHERE  l.Usuario = u.nombre AND l.Usuario = '"+nomUsuario+"' ";
        int numFiles = getNumRowsQuery(qn);
        String[] info = new String[numFiles];
        try {
            String q = "SELECT l.título AS TITULO FROM lista l, usuario u WHERE l.Usuario = u.nombre AND l.Usuario = '"+nomUsuario+"' ORDER BY TITULO ASC";

            ResultSet rs = query.executeQuery(q);
            int nr = 0;
            while (rs.next()) {
                info[nr] = rs.getString("TITULO");
                nr++;
            }
            return info;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public String[][] getInfoTaulaCanciones(String nomUsuario){
        int numFiles = getNumRowsTaula("canción");
        int numCols  = 4;
        String[][] info = new String[numFiles][numCols];
        try {
            ResultSet rs = query.executeQuery( "SELECT c.título AS TITULO, c.Lista_título AS LISTA, c.favorito AS FAVORITO FROM canción c ORDER BY TITULO ASC");
            int nr = 0;
            while (rs.next()) {
                info[nr][0] = String.valueOf(nr+1);
                info[nr][1] = rs.getString("TITULO");
                info[nr][2] = rs.getString("LISTA");
                info[nr][3] = rs.getString("FAVORITO");
                nr++;
            }
            return info;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }


    public String[][] getInfoTaulaFavoritos(){
        String qn = "SELECT COUNT(*) AS n FROM canción c WHERE c.favorito = '1'";
        int numFiles = getNumRowsQuery(qn);
        int numCols  = 3;
        String[][] info = new String[numFiles][numCols];
        try {
            Statement query = c.createStatement();
            String q = "SELECT c.título AS TITULO, c.Lista_título AS LISTA, c.orden AS ORDEN FROM canción c WHERE c.favorito = '1'";
            ResultSet rs = query.executeQuery( q);
            int nr = 0;
            while (rs.next()) {
                info[nr][0] = rs.getString("ORDEN");
                info[nr][1] = rs.getString("LISTA");
                info[nr][2] = rs.getString("TITULO");
                nr++;
            }
            return info;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public String[][] getInfoTaulaFavoritos(String nomUsuario){
        String qn ="SELECT COUNT(*) AS n "+
                    "FROM canción c , usuario u, lista l "+
                    "WHERE l.Usuario = u.nombre AND l.título=c.Lista_título AND c.favorito = '1' AND u.nombre='" + nomUsuario + "' " ;
        int numFiles = getNumRowsQuery(qn);
        int numCols  = 3;
        String[][] info = new String[numFiles][numCols];
        try {
            String q = "SELECT c.título AS TITULO, c.Lista_título AS LISTA, c.orden AS ORDEN "+
                       "FROM canción c , usuario u, lista l "+
                        "WHERE l.Usuario = u.nombre AND l.título=c.Lista_título AND c.favorito = '1' AND u.nombre='" + nomUsuario + "' " +
                        "ORDER BY ORDEN ASC";
            Statement query = c.createStatement();
            ResultSet rs = query.executeQuery(q);
            int nr = 0;
            while (rs.next()) {
                info[nr][0] = rs.getString("ORDEN");
                info[nr][1] = rs.getString("LISTA");
                info[nr][2] = rs.getString("TITULO");
                nr++;
            }
            return info;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public String [][] getInfoTaulaTusCanciones (String nomUsuario){
        int numFiles = getNumRowsTaula("canción");
        int numCols  = 3;
        String[][] info = new String[numFiles][numCols];
        try {
            Statement query = c.createStatement();
            ResultSet rs = query.executeQuery( "SELECT c.título AS TITULO, c.Lista_título AS LISTA, c.orden AS ORDEN FROM canción c ");
            int nr = 0;
            while (rs.next()) {
                info[nr][0] = rs.getString("ORDEN");
                info[nr][1] = rs.getString("LISTA");
                info[nr][2] = rs.getString("TITULO");
                nr++;
            }
            return info;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public String [][] getInfoTaulaTusListasCateg3(String nomUsuario){
        String qn ="SELECT COUNT(*) As n FROM lista l, usuario u, categoría c WHERE l.Usuario = u.nombre AND l.Usuario = '"+nomUsuario+"' AND l.Categoría = c.idCategoría AND l.Categoría = '3'";
        int numFiles = getNumRowsQuery(qn);
        int numCols  = 3;
        String[][] info = new String[numFiles][numCols];
        try {
            String q ="SELECT l.numCanciones AS NUM, l.título AS TITULO, c.nombre AS CATEGORIA FROM lista l, usuario u, categoría c WHERE l.Usuario = u.nombre AND l.Usuario = '"+nomUsuario+"' AND l.Categoría = c.idCategoría AND l.Categoría = '3' ORDER BY l.orden ASC";
            ResultSet rs = query.executeQuery(q);
            int nr = 0;
            while (rs.next()) {
                info[nr][0] = String.valueOf(rs.getString("NUM"));
                info[nr][1] = rs.getString("TITULO");
                info[nr][2] = rs.getString("CATEGORIA");
                nr++;
            }
            return info;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public String [][] getInfoTaulaTusListas(String nomUsuario){
        String qn ="SELECT COUNT(*) As n FROM lista l, usuario u, categoría c WHERE l.Usuario = u.nombre AND l.Usuario = '"+nomUsuario+"' AND l.Categoría = c.idCategoría";
        int numFiles = getNumRowsQuery(qn);
        int numCols  = 3;
        String[][] info = new String[numFiles][numCols];
        try {
            String q ="SELECT l.numCanciones AS NUM, l.título AS TITULO, c.nombre AS CATEGORIA FROM lista l, usuario u, categoría c WHERE l.Usuario = u.nombre AND l.Usuario = '"+nomUsuario+"' AND l.Categoría = c.idCategoría ORDER BY l.orden ASC";
            ResultSet rs = query.executeQuery(q);
            int nr = 0;
            while (rs.next()) {
                info[nr][0] = String.valueOf(rs.getString("NUM"));
                info[nr][1] = rs.getString("TITULO");
                info[nr][2] = rs.getString("CATEGORIA");
                nr++;
            }
            return info;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public String [][] getInfoListasCategoria(String nomUsuario, String nombreCategoria){
        String qn = "SELECT COUNT(*) AS n "+
                    "FROM lista l, usuario u, categoría c "+
                    "WHERE l.Usuario = u.nombre AND l.Usuario = '"+nomUsuario+"' AND l.Categoría = c.idCategoría AND c.nombre='"+nombreCategoria+"' ";
        int numFiles = getNumRowsQuery(qn);
        int numCols  = 4;
        String[][] info = new String[numFiles][numCols];
        try {
            String q ="SELECT l.título AS TITULO, l.subtítulo AS SUBTITULO, l.numCanciones as NUM, l.orden AS ORDEN "+
                      "FROM lista l, usuario u, categoría c "+
                       "WHERE l.Usuario = u.nombre AND l.Usuario = '"+nomUsuario+"' AND l.Categoría = c.idCategoría AND c.nombre='"+nombreCategoria+"' "+
                       "ORDER BY l.orden ASC";
            ResultSet rs = query.executeQuery(q);
            int nr = 0;
            while (rs.next()) {
                info[nr][0] = String.valueOf(rs.getString("ORDEN"));
                info[nr][1] = rs.getString("TITULO");
                info[nr][2] = rs.getString("SUBTITULO");
                info[nr][3] = rs.getString("NUM");
                nr++;
            }
            return info;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public String[][] getInfoCancionesLista(String idLista){
        String qn ="SELECT COUNT(*) As n FROM canción c, lista l WHERE c.Lista_título = l.título AND l.título = '"+idLista+"' ORDER BY c.orden ASC";
        int numFiles = getNumRowsQuery(qn);
        int numCols  = 3;
        String[][] info = new String[numFiles][numCols];
        try {
            String q ="SELECT c.orden AS ORDEN, c.título AS TITULO, c.día AS DIA FROM canción c, lista l " +
                      "WHERE c.Lista_título = l.título AND l.título = '"+idLista+"' ORDER BY c.orden ASC";
            ResultSet rs = query.executeQuery(q);
            int nr = 0;
            while (rs.next()) {
                info[nr][0] = String.valueOf(rs.getInt("ORDEN"));
                info[nr][1] = rs.getString("TITULO");
                info[nr][2] = rs.getString("DIA");
                nr++;
            }
            return info;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }


    public String[][] getInfoCancionesCategoria(String nomUsuario, String nomCategoria){
        String qn = "SELECT COUNT(*) AS n " +
                " FROM canción c, lista l, categoría cat, usuario u " +
                " WHERE l.Categoría = cat.idCategoría AND c.Lista_título=l.título AND l.Usuario= u.nombre " +
                "  AND u.nombre='"+nomUsuario+"' AND cat.nombre='"+nomCategoria+"' ";
        int numFiles = getNumRowsQuery(qn);
        int numCols  = 4;
        String[][] info = new String[numFiles][numCols];
        try {
            String q = "SELECT c.título AS TITULO, c.día AS DIA, c.Lista_título AS LISTA, c.orden AS ORDEN, c.favorito AS FAVORITO " +
                     " FROM canción c, lista l, categoría cat, usuario u " +
                     " WHERE l.Categoría = cat.idCategoría AND c.Lista_título=l.título AND l.Usuario= u.nombre " +
                     "  AND u.nombre='nuriafemeniass' AND cat.nombre='Barra' " +
                     " ORDER BY c.título ASC";
            ResultSet rs = query.executeQuery( q);
            int nr = 0;
            while (rs.next()) {
                info[nr][0] = String.valueOf(nr+1);
                info[nr][1] = rs.getString("TITULO");
                info[nr][2] = rs.getString("LISTA");
                info[nr][3] = rs.getString("FAVORITO");
                nr++;
            }
            return info;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }


    // Retorna les dades de la columna NOM de la taula UNITAT
    public String[] getColumnaNomTaulaUnitat(){
        int numFiles = getNumRowsTaula("unitat");
        String[] info = new String[numFiles];
        try {
            ResultSet rs = query.executeQuery( "SELECT nom FROM unitat ORDER BY nom ASC");
            int nr = 0;
            while (rs.next()) {
                info[nr] = rs.getString("nom");
                nr++;
            }
            return info;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    // Retorna el valor de la Columna NUMERO de la taula UNITAT per aquella fila amb NOM
    public String getNumeroFromTaulaUnitat(String nom)  {
        try {
            ResultSet rs = query.executeQuery( "SELECT numero FROM unitat WHERE nom = '"+nom+"'");
            rs.next();
            return String.valueOf(rs.getInt("numero"));
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public String getClaveFromTabla(String nombreTable, String nombreClave, String nombreColumna, String valorColumna){
        try {
            String q = "SELECT "+nombreClave+" AS clave FROM "+nombreTable+" WHERE "+nombreColumna+"='"+valorColumna+"'";
            ResultSet rs = query.executeQuery( q);
            rs.next();
            return rs.getString("clave");
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }



    // INSERTS

    // Inserta les dades a la taula Llista

    public void insertLista(String titulo, String subtitulo, String categoria){
        try {
            String q = "INSERT INTO lista (título, subtítulo, numCanciones, Usuario, Categoría, orden) VALUES ('" + titulo + "', '" + subtitulo + "', '0', 'nuriafemeniass', '" + categoria + "', '36')";
            System.out.println(q);
            query.execute(q);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public void insertCancion(String titulo, String dia, String categoria, String favorito){
        try {
            ////////    INSERT INTO `canción` (`título`, `día`, `Lista_título`, `favorito`, `orden`) VALUES ('olaaaa', '2024-03-13', 'Allegro 3', '1', '3');
            String q = "INSERT INTO canción (título, día, Lista_título, favorito, orden) VALUES ('" + titulo + "', '" + dia + "','"+categoria+"','"+favorito+"', '50')";
            System.out.println(q);
            query.execute(q);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }



    // DELETES

    // Esborra la fila de la taula Unitat amb el número concret
    public void deleteInfoTaulaLista(String titulo){
        try {
            // DELETE FROM lista WHERE `lista`.`título` = 'hh'
            String q = "DELETE FROM lista WHERE `lista`.`título` = '"+titulo+"'"; //borrar una llista
            System.out.println(q);
            query.execute(q);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }



    // UPDATES

    // Actualitza el campo FAVORITO de una determinada canción

    public void updateFavoritoCancion(String idCancion, boolean favorito){
        String valorFavorito = favorito ? "1" : "0";
        String q = "UPDATE canción SET favorito = '" + valorFavorito + "' WHERE título = '" + idCancion + "' ";
        System.out.println(q);
        try {
            query.execute(q);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }


    public void printArray2d(String[][] array){
        for(int i=0; i<array.length; i++){
            for(int j=0; j<array[i].length; j++){
                System.out.print(array[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public void printArray1d(String[] array){
        for(int i=0; i<array.length; i++){
            System.out.println(array[i]);
        }
    }

    // Conversiones entre fechas

    public String formataFechaEsp(String fechaEntrada){

        String y = fechaEntrada.split("-")[0];
        String m = fechaEntrada.split("-")[1];
        String d = fechaEntrada.split("-")[2];

        return d+"/"+m+"/"+y;
    }

    public String formataFechaEng(String fechaEntrada){

        String y = fechaEntrada.split("/")[2];
        String m = fechaEntrada.split("/")[1];
        String d = fechaEntrada.split("/")[0];

        return y+"-"+m+"-"+d;
    }
}
