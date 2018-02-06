/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    private TextArea Text;
    private ScrollPane scrollReceivedText;
    private TextFlow receivedText;
    private Button sendBtn;
    private Button clearBtn;
    private TextArea connected;
    private Text textMembers;
    
    ClientPanel(){
        
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
        
        sendBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    envoi();
                    Text.setText("");
                }
        });
        
        clearBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Text.setText("");
            }
        });
        
        //envoyer le message lorsqu'on fait "entrer"
        Text.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    envoi();
                    Text.setText("");
                }
            }
        });
        
        this.getChildren().add(Text);
        this.getChildren().add(scrollReceivedText);
        this.getChildren().add(receivedText);
        this.getChildren().add(sendBtn);
        this.getChildren().add(clearBtn);
        this.getChildren().add(connected);
        this.getChildren().add(textMembers);
    }
 
    public void envoi(){
        String message = Text.getText();
        if(message.length() != 0){
            Label label = new Label(message);
            label.setWrapText(true);
            label.setPrefWidth(380);
            receivedText.getChildren().add(label);
        }
    }
}   
