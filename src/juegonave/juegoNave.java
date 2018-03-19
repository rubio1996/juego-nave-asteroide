/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegonave;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author 1daw
 */
   
public class juegoNave extends Application {
    double posicionX = 150.0;
    double posicionY = 00.0; 
    public Pane root = new Pane();
    asteroide asteroide;
    asteroide borrarasteroide;
    Nave nave = new Nave();
    Bala bala;
    Text textScore;
    Shape punto;
    String TEXT_SIZE;
    Bala borrarbala;
    int score;
    int hightscore;
    private int estilos = 1;
    int numeroAsteroides = 3;
    int numBala = 50;
    AnimationTimer animationMovimiento;
    @Override
    public void start(Stage primaryStage1) {
        // Organizar los elementos en la ventana
        /* CREACIÓN DE LA VENTANA Y DE LOS CONTENEDORES PRINCIPALES */
        
        // Contenedor principal donde se alojarán todos los elementos
        // Creación del área (scene) correspondiente al contenido que tendrá la 
        //  ventana, de 600 x 400 puntos, con color gris claro, indicando que el
        //  elemento root va a ser el contenedor principal de este espacio
        Scene scene = new Scene(root, nave.SCENE_TAM_X, 800);
        scene.getStylesheets().add("img/estilo4.css");
        // Se asocia la ventana (scene) al parámetro primaryStage (escenario
        //  principal). El parámetro primaryStage se recibe en este método start
        primaryStage1.setScene(scene);
        // Título que se aparecerá en la ventana
        primaryStage1.setTitle("dibujando el JUEGO DE NAVECITAS");
        // Orden para que se muestre la ventana        
        primaryStage1.show();
        //LAYOUTS PARA MOSTRAR PUNTUACIONES
        //Layout principal
        HBox paneScore = new HBox();
        paneScore.setTranslateY(20);
        paneScore.setMinWidth(nave.SCENE_TAM_X);
        paneScore.setAlignment(Pos.CENTER);
        paneScore.setSpacing(100);
        root.getChildren().add(paneScore);
        //Layout para puntuacion actual
        HBox paneCurrentScore = new HBox();
        paneCurrentScore.setSpacing(10);
        paneScore.getChildren().add(paneCurrentScore);
        //Layout para puntuacion maxima
        HBox paneHightScore = new HBox();
        paneHightScore.setSpacing(10);
        paneScore.getChildren().add(paneHightScore);
        //Texto de etiqueta para la puntuacion
        Text textTitleScore = new Text("Score:");
        textTitleScore.setFont(Font.font(TEXT_SIZE));
        textTitleScore.setFill(Color.WHITE);
        //Texto para la puntuacion
        textScore = new Text("0");
        textScore.setFont(Font.font(TEXT_SIZE));
        textScore.setFill(Color.WHITE);
        //Texto de etiqueta para la puntuacion maxima
        Text textTitleHighScore = new Text("Max.Score");
        textTitleHighScore.setFont(Font.font(TEXT_SIZE));
        textTitleHighScore.setFill(Color.WHITE);
        //Texto para la puntuacion maxima
        Text textHighScore = new Text("0");
        textHighScore.setFont(Font.font(TEXT_SIZE));
        textHighScore.setFill(Color.WHITE);
        //Añadir los textos a los layouts reservados para ellos
        paneCurrentScore.getChildren().add(textTitleScore);
        paneCurrentScore.getChildren().add(textScore);
        paneHightScore.getChildren().add(textTitleHighScore);
        paneHightScore.getChildren().add(textHighScore);
        ArrayList<Bala> listabala = new ArrayList(); 
        
        ArrayList<asteroide> listaasteroides = new ArrayList(); 
        for (int a = 0; a < numeroAsteroides; a++) {
            asteroide asteroide = new asteroide(300, 500, 2 );
            listaasteroides.add(asteroide);
         root.getChildren().add(asteroide.getPolygon());
           
       }
        //////////////////////////Creacion de la forma del meteorito///////////////////////////////////
        root.getChildren().add(nave.getGroup());
        nave.getPolygon().setId("poligono1");
        nave.getPolygon1().setId("poligono2");
        nave.getPolygon2().setId("poligono3");
        nave.getPolygon3().setId("poligono4");
        nave.getPolygon4().setId("poligono5");
        nave.getPolygon5().setId("poligono6");
        ///////////////////////////creacion bala //////////////////////////////////////////////
        nave.getGroup().setLayoutX(posicionX);
        nave.getGroup().setLayoutY(posicionY);
        ImageView estilo = new ImageView("img/icono.png");
        root.getChildren().add(estilo);
        estilo.setFitHeight(50);
        estilo.setLayoutY(scene.getHeight()-50);
        estilo.setPreserveRatio(true);
            // Limpiar los estilos que tuviera anteriormente
            estilo.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
                public void handle(MouseEvent event) {
                scene.getStylesheets().clear();
                    switch(estilos){
                    case 1: 
                        scene.getStylesheets().add("img/estilo1.css");
                        break;
                    case 2:
                        scene.getStylesheets().add("img/estilo2.css");
                        break;
                    case 3:
                        scene.getStylesheets().add("img/estilo3.css");
                        break;
                    case 4:
                        scene.getStylesheets().add("img/estilo4.css");
                        estilos = 0;
                        break;
                    }
               estilos++;
                }
            });       
        scene.setOnKeyPressed((KeyEvent event) -> {
            //Crearemos un swith que es como una sentencia de if  pero tiene multiples opciones para selecionar la tecla  y el evento
            switch(event.getCode()){
                case UP: 
                    //pulsada tecla arriba
                    nave.rotAcel = true;
                   
                    break;
                case LEFT:
                    //pulsada tecla hacia un lado
                    nave.rotIzq = true;
                    break;
                case RIGHT:
                    //pulsada tecla hacia otro lado
                    nave.rotDer = true;
                    break;
                case SPACE:
                    bala = new Bala(nave.rotacionNave(), nave.getGroup().getLayoutX(), nave.getGroup().getLayoutY());
                    listabala.add(bala);
                    root.getChildren().add(bala.getPolygon());
                    break;
                case DOWN:
                    nave.frenarNave();
                case R:
                    break;
            }
        });
        scene.setOnKeyReleased((KeyEvent event) -> {
                 //Crearemos un swith que es como una sentencia de if  pero tiene multiples opciones para selecionar la tecla  y el evento
            switch(event.getCode()){
                case UP: 
                    //pulsada tecla arriba
                    nave.rotAcel = false;
                     
                    break;
                case LEFT:
                    //pulsada tecla hacia un lado
                    nave.rotIzq = false;
                    break;
                case RIGHT:
                    //pulsada tecla hacia otro lado
                    nave.rotDer = false;
                    break;
                case R:
                    break;
            }
        });

        //////////////////////////TERMINACION CREACION BALA///////////////////////////////////////
        animationMovimiento = new AnimationTimer(){
            @Override
            public void handle(long now) {
            nave.getGroup().setLayoutX(nave.getGroup().getLayoutX()+nave.velocidadX);
            nave.getGroup().setLayoutY(nave.getGroup().getLayoutY()+nave.velocidadY);
            nave.getGroup().setRotate(nave.rotacionNave());
            for(asteroide asteroideActual : listaasteroides){
                asteroideActual.rotarAsteroide();
                asteroideActual.salirPantalla(nave.SCENE_TAM_X, nave.SCENE_TAM_Y);
                nave.chocarNaveAsteroide(asteroideActual, root);
                //si la nave choca con el asteroide
                if (nave.chocarNaveAsteroide(asteroideActual, root) == false){
                    //pararemos la animacion
                        animationMovimiento.stop();
                        //agregaremos un texto Con las letras de game over
                        Label label = new Label("GAME OVER");
                        //le daremos la fuentes georgia y un tamaño a la fuente
                        label.setFont(new Font("Georgia", 80));
                        //le pondremos un color al texto
                        label.setTextFill(Color.web("FFFFFF"));
                        //el texto lo posicionaremos en la mitad del eje x
                        label.setLayoutX(nave.SCENE_TAM_X/2);
                        //el texto lo posicionaremos en la mitad del eje Y
                        label.setLayoutY(nave.SCENE_TAM_Y/2);
                        //añadiremos el label al root
                        root.getChildren().add(label);
                }
            }
            //recorrer lista de las balas
          for(int i=0; i<listabala.size(); i++){
              //coje una bala de la lista
                Bala bala = listabala.get(i);
                bala.moverBala();
                //recorre lista de asteroide
                for (int o=0; o<listaasteroides.size(); o++){
                    //coje un asteroide de la lista
                    asteroide asteroide = listaasteroides.get(o);
                    //guardo la colision llamadpunto
                    punto = Shape.intersect(bala.getPolygon(),asteroide.getPolygon());
                    //es un boleean para comprobar si la variable punto esta vacia
                    boolean puntoVacio = punto.getBoundsInLocal().isEmpty();
                    //si el bolean no esta vacio
                    if (puntoVacio == false){
                        //cojemos el asteroide
                        borrarasteroide = listaasteroides.get(o);
                        //cojemos la bala
                        borrarbala = listabala.get(i);
                        //borramos la bala 
                        listabala.remove(borrarbala);
                        //borramos el asteroide
                        listaasteroides.remove(borrarasteroide);
                        score++;
                        textScore.setText(String.valueOf(score));
                    }
                }  
            }
            nave.creacioncoseno();
            nave.CreacionSeno();
            nave.aceleracion();
            nave.rotacionNave();
            nave.salirPantalla();
            }
        };
        animationMovimiento.start();            
        }

   
    public static void main(String[] args) {
        launch(args);
    }
    
}