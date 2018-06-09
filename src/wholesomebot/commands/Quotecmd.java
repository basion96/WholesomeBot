package wholesomebot.commands;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import wholesomebot.main.ResponseMessages;
import java.util.Random;

public class Quotecmd extends Command{

    private String[] quotes = ResponseMessages.getQuotes();

    @Override
    public void onMessageReceived(MessageReceivedEvent event){

        MessageChannel channel = event.getChannel();
        String msg = event.getMessage().getContentDisplay();

        if(!event.getAuthor().isBot()){
            if(msg.equalsIgnoreCase(prefix+"quote")){
                channel.sendMessage(quotes[new Random().nextInt(quotes.length)]).queue();
            }
        }
    }
}
