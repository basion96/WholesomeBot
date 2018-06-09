package wholesomebot.commands;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import wholesomebot.main.ResponseMessages;

import java.io.File;
import java.util.Random;

public class WholesomeImgcmd extends Command {

    private File[] imgs = ResponseMessages.imgs;

    public void onMessageReceived(MessageReceivedEvent event){

        MessageChannel channel = event.getChannel();
        String msg = event.getMessage().getContentDisplay();

        if(!event.getAuthor().isBot()){
            if(msg.equalsIgnoreCase(prefix+"wholesomeimg")){
                channel.sendFile(imgs[new Random().nextInt(imgs.length)]).queue();
            }
        }
    }
}