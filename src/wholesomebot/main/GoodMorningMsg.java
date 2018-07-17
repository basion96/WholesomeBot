package wholesomebot.main;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;

import java.util.ArrayList;
import java.util.Random;

public class GoodMorningMsg {

    private TextChannel channel;
    private Guild guild;
    private ArrayList<String> messagedUsers;
    private ArrayList<Member> members;
    private static boolean newMember;

    public GoodMorningMsg(){
        channel = WholesomeBot.jda.getTextChannelById(WholesomeProperties.getPublicChannel());
        guild = channel.getGuild();
        newMember = false;
        messagedUsers = new ArrayList<>();
        members = new ArrayList<>();
        members.addAll(guild.getMembers());
    }

    public void sendMessage(){
        if(newMember)
            updateMembers();

        if(messagedUsers.size()==members.size()-1)
            messagedUsers.clear();

        String user;
        do{
            user = members.get(new Random().nextInt(members.size())).getUser().getId();
        }while(hasBeenMessaged(user) || user.equals(WholesomeBot.jda.getSelfUser().getId()));

        channel.sendMessage("Good morning <@" + user + ">, have a great day today :blush:").queue();

        WholesomeProperties.saveMorningMessageUser(user);
    }

    private boolean hasBeenMessaged(String user){
        if(messagedUsers.contains(user) || user.equals(WholesomeBot.jda.getSelfUser().getId())){
            return true;
        }
        else{
            messagedUsers.add(user);
            return false;
        }
    }

    private void updateMembers(){
        members = new ArrayList<>();
        members.addAll(guild.getMembers());
    }

    public static void newMember(){
        newMember=true;
    }
}
