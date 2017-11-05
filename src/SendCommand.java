import Utils.ChatUtils;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class SendCommand extends Command {

    private String prefix = "&9&lSending &8>> &7";

    public SendCommand() {
        super("bungeesend");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("bungeesend.use")) {
            if (args.length > 1) {
                ProxiedPlayer target = BungeeCord.getInstance().getPlayer(args[0]);
                if (target == null) sender.sendMessage(ChatUtils.format(prefix + "That player isn't online or doesn't exist!"));
                else {
                    ServerInfo server = ProxyServer.getInstance().getServerInfo(args[1]);
                    if (server == null)
                        sender.sendMessage(ChatUtils.format(prefix + "That server isn't a server! Avaliable servers: lobby, testing, server"));
                    else {
                        target.connect(server);
                        target.sendMessage(ChatUtils.format(prefix + " You have been sent to &c" + server.getName() + "&7 by &c" + sender.getName()));
                        sender.sendMessage(ChatUtils.format(prefix + "You have sent &c" + target.getName() + "&7 to &c" + server.getName()));
                    }
                }
            } else {
                sender.sendMessage(ChatUtils.format(prefix + "Invalid usage! Usage: /bungeesend <player> <server>"));
            }
        } else {
            sender.sendMessage(ChatUtils.format(prefix + "You don't have permission to use this command!"));
        }
    }
}
