import Utils.ChatUtils;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class PingCommand extends Command {

    private String prefix = "&9&lPing &8>> &7";

    public PingCommand() {
        super("bungeeping");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length != 0) {
            ProxiedPlayer target = BungeeCord.getInstance().getPlayer(args[0]);
            if (target != null) {
                sender.sendMessage(ChatUtils.format(prefix + "&c" + target.getName() + "'s&7 ping is &c" + target.getPing()));
            }
            else sender.sendMessage(ChatUtils.format(prefix + "That player isn't online or doesn't exist!"));
        } else {
            if (sender instanceof ProxiedPlayer) sender.sendMessage(ChatUtils.format(prefix + "Your ping is &c" + ((ProxiedPlayer) sender).getPing()));
            else sender.sendMessage(ChatUtils.format(prefix + "You're unable to use this command!"));
        }
    }

}
