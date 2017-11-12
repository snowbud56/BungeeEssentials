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
        super("bungeesend", "bungeecord.send", "s", "send");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length > 1) {
            String arg1 = args[0].toLowerCase();
            String arg2 = args[1].toLowerCase();
            if (arg2.equals("here") && !(sender instanceof ProxiedPlayer)) {
                sender.sendMessage(ChatUtils.format(prefix + "Only players can use \"here\" as the server!"));
                return;
            }
            if (arg1.equals("all")) {
                if (arg2.equals("here")) {
                    ServerInfo targetserver = ((ProxiedPlayer) sender).getServer().getInfo();
                    for (ProxiedPlayer target : BungeeCord.getInstance().getPlayers()) teleport(target, sender, targetserver);
                } else {
                    ServerInfo targetserver = BungeeCord.getInstance().getServerInfo(arg2);
                    if (targetserver == null) {
                        String servers = "";
                        for (ServerInfo servername : BungeeCord.getInstance().getServers().values()) servers += servername.getName() + ", ";
                        sender.sendMessage(ChatUtils.format(prefix + "That isn't a valid server! Avaliable servers: " + servers));
                    } else {
                        for (ProxiedPlayer player : BungeeCord.getInstance().getPlayers()) teleport(player, sender, targetserver);
                    }
                }
            } else {
                ProxiedPlayer target = BungeeCord.getInstance().getPlayer(arg1);
                if (target == null) sender.sendMessage(ChatUtils.format(prefix + "That player doesn't exist or isn't online!"));
                else {
                    if (arg2.equals("here")) {
                        ServerInfo targetserver = ((ProxiedPlayer) sender).getServer().getInfo();
                        teleport(target, sender, targetserver);
                    } else {
                        ServerInfo targetserver = BungeeCord.getInstance().getServerInfo(arg2);
                        if (targetserver == null) {
                            String servers = "";
                            for (ServerInfo servername : BungeeCord.getInstance().getServers().values())
                                servers += servername.getName() + ", ";
                            sender.sendMessage(ChatUtils.format(prefix + "That isn't a valid server! Avaliable servers: " + servers));
                        } else {
                            teleport(target, sender, targetserver);
                        }
                    }
                }
            }
        } else {
            sender.sendMessage(ChatUtils.format(prefix + "Invalid usage! Usage: /bungeesend <player> <server>"));
        }
    }

    private void teleport(ProxiedPlayer target, CommandSender player, ServerInfo server) {
        target.connect(server);
        target.sendMessage(ChatUtils.format(prefix + " You have been sent to &c" + server.getName() + "&7 by &c" + player.getName()));
        player.sendMessage(ChatUtils.format(prefix + "You have sent &c" + target.getName() + "&7 to &c" + server.getName()));
    }
}
