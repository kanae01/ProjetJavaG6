
package monchat;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 *
 * @author thoma
 */
class ClientPanel extends Parent {
    
    //initialisation des élements de la fenêtre
    private TextArea Text;
    private ScrollPane scrollReceivedText;
    private TextFlow receivedText;
    private Button sendBtn;
    private Button clearBtn;
    private TextArea connected;
    private Text textMembers;
    
    public ClientPanel(){
        
        Text = new TextArea();
        scrollReceivedText = new ScrollPane();
        receivedText = new TextFlow();
        sendBtn = new Button("Envoyer");
        clearBtn = new Button("Effacer");
        connected = new TextArea();
        textMembers = new Text("connectés :");
        
        textMembers.setLayoutX(470);
        textMembers.setLayoutY(40);
        
        sendBtn.setLayoutX(470);
        sendBtn.setLayoutY(350);
        sendBtn.setPrefHeight(15);
        sendBtn.setPrefWidth(100);
        sendBtn.setVisible(true);
        
        clearBtn.setLayoutX(470);
        clearBtn.setLayoutY(380);
        clearBtn.setPrefHeight(15);
        clearBtn.setPrefWidth(100);
        clearBtn.setVisible(true);
        
        connected.setLayoutX(470);
        connected.setLayoutY(50);
        connected.setPrefHeight(280);
        connected.setPrefWidth(100);
        connected.setEditable(false);
        
        Text.setLayoutX(50);
        Text.setLayoutY(350);
        Text.setPrefHeight(100);
        Text.setPrefWidth(400);
        Text.setWrapText(true);
        
        scrollReceivedText.setLayoutX(50);
        scrollReceivedText.setLayoutY(50);
        scrollReceivedText.setPrefHeight(280);
        scrollReceivedText.setPrefWidth(400);
        
        scrollReceivedText.setContent(receivedText);
        scrollReceivedText.vvalueProperty().bind(receivedText.heightProperty());
        
        receivedText.setVisible(true);
        receivedText.setLineSpacing(5);
        receivedText.setPrefWidth(380);
        
        //listener du bouton "Envoyer"
        sendBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    envoi(); //envoie du message
                    Text.setText(""); //suppression du contenu de la TextArea
                }
        });
        
        //listener du bouton "Effacer"
        clearBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String message = MonChat.c.receive();
                
                // if(message != null && message != ""){
                    Label label = new Label(message);
                    label.setWrapText(true);
                    label.setPrefWidth(380);
                    receivedText.getChildren().add(label);
                // }
                
                Text.setText("");
            }
        });
        
        //envoyer le message lorsqu'on fait "entrer"
        Text.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    //Text.setText(Text.getText().substring(0,Text.getText().length() - 1));
                    envoi(); //envoie du message
                    Text.setText(""); //suppression du contenu de la TextArea
                }
            }
        });
        
        //insertion des élements dans la fenêtre
        this.getChildren().add(Text);
        this.getChildren().add(scrollReceivedText);
        this.getChildren().add(receivedText);
        this.getChildren().add(sendBtn);
        this.getChildren().add(clearBtn);
        this.getChildren().add(connected);
        this.getChildren().add(textMembers);
    }

    /**
     * envoie le texte contenu dans la TextArea vers le Textflow après suppression des espaces vides
     */
    public void envoi(){
        String message = Text.getText();
        
        //suppression des espaces blancs au début de la chaine
        while(message.length() != 0 && (message.charAt(0)=='\n' || message.charAt(0)==' '))
        {
            message = message.substring(1);
        }
        //suppression des espaces blancs à la fin de la chaine
        while(message.length() != 0 && (message.charAt(message.length() - 1)=='\n' || message.charAt(message.length() - 1)==' '))
        {
            message = message.substring(0,message.length() - 1);
        }

        //si le message n'est pas vide : on l'insère
        MonChat.c.send(message);
        if(message.length() != 0){
            Label label = new Label(message);
            label.setWrapText(true);
            label.setPrefWidth(380);
            receivedText.getChildren().add(label);
        }
    }

}
