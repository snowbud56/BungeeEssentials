import Utils.ChatUtils;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.Title;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class AnnounceCommand extends Command {

    private String prefix = "&9&lAnnounce &8>> &7";

    public AnnounceCommand() {
        super("announce");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("BungeeAnnounce.announce")) {
            if (args.length != 0) {
                String msg = "";
                for (int i = 0; i < args.length; i++) msg += args[i] + " ";
                BungeeCord.getInstance().broadcast(ChatUtils.format("&b&lAnnouncement &8>> &c" + msg));
                Title announcement = BungeeEssentials.getInstance().getProxy().createTitle();
                announcement.fadeIn(20).stay(200).fadeOut(20);
                announcement.title(ChatUtils.format("&9&lAnnouncement"));
                announcement.subTitle(ChatUtils.format("&b" + msg));
                for (ProxiedPlayer player : BungeeCord.getInstance().getPlayers()) announcement.send(player);
            } else {
                sender.sendMessage(ChatUtils.format(prefix + "Please provide a message to broadcast!"));
            }
        } else {
            sender.sendMessage(ChatUtils.format(prefix + "You don't have permission to use this command!"));
        }
    }

}
