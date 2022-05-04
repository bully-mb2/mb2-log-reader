package com.templars_server.parser.MBParser;

import generated.InitGameEvent;

import java.util.regex.Matcher;

public class InitGameParser extends MBEvent<InitGameEvent> {

    public InitGameParser() {
        super("^InitGame: (.*)$");
    }

    @Override
    protected InitGameEvent parseEvent(Matcher matcher) {
        try {
            InitGameEvent initGameEvent = new InitGameEvent();

            InfoMap infoMap = parseInfoMap(matcher.group(1));
            initGameEvent.setVersion(infoMap.getString("version"));
            initGameEvent.setTimeLimit(infoMap.getInt("timelimit"));
            initGameEvent.setSvPrivateClients(infoMap.getInt("sv_privateClients"));
            initGameEvent.setSvMinRate(infoMap.getInt("sv_minRate"));
            initGameEvent.setSvMinPing(infoMap.getInt("sv_minPing"));
            initGameEvent.setSvMaxRate(infoMap.getInt("sv_maxrate"));
            initGameEvent.setSvMaxClients(infoMap.getInt("sv_maxclients"));
            initGameEvent.setSvMaxPing(infoMap.getInt("sv_maxPing"));
            initGameEvent.setSvHostname(infoMap.getString("sv_hostname"));
            initGameEvent.setSvFps(infoMap.getInt("sv_fps"));
            initGameEvent.setSvFloodProtectSlow(infoMap.getInt("sv_floodProtectSlow"));
            initGameEvent.setSvFloodProtect(infoMap.getInt("sv_floodProtect"));
            initGameEvent.setSvAutoDemo(infoMap.getInt("sv_autoDemo"));
            initGameEvent.setSvAllowDownload(infoMap.getInt("sv_allowdownload"));
            initGameEvent.setProtocol(infoMap.getInt("protocol"));
            initGameEvent.setMapName(infoMap.getString("mapname"));
            initGameEvent.setLocation(infoMap.getString("location"));
            initGameEvent.setGameName(infoMap.getString("gamename"));
            initGameEvent.setGTeamSwap(infoMap.getInt("g_teamSwap"));
            initGameEvent.setGSiegeTeam1(infoMap.getString("g_siegeTeam1"));
            initGameEvent.setGSiegeTeam2(infoMap.getString("g_siegeTeam2"));
            initGameEvent.setGSaberLocking(infoMap.getInt("g_saberLocking"));
            initGameEvent.setGPrivateDuel(infoMap.getInt("g_privateDuel"));
            initGameEvent.setGNeedPass(infoMap.getInt("g_needpass"));
            initGameEvent.setGMaxHolocronCarry(infoMap.getInt("g_maxHolocronCarry"));
            initGameEvent.setGMaxForceRank(infoMap.getInt("g_maxForceRank"));
            initGameEvent.setGJediVsMerc(infoMap.getInt("g_jediVmerc"));
            initGameEvent.setGGravity(infoMap.getInt("g_gravity"));
            initGameEvent.setGGameType(infoMap.getInt("g_gametype"));
            initGameEvent.setGForceBasedTeams(infoMap.getInt("g_forceBasedTeams"));
            initGameEvent.setGDuelWeaponDisable(infoMap.getInt("g_duelWeaponDisable"));
            initGameEvent.setGCompetitive(infoMap.getInt("g_competitive"));
            initGameEvent.setGShuffleTimer(infoMap.getInt("g_ShuffleTimer"));
            initGameEvent.setGDuelTimeLimit(infoMap.getInt("g_DuelTimeLimit"));
            initGameEvent.setGDuelFriendlyTeam(infoMap.getInt("g_DuelFriendlyTeam"));
            initGameEvent.setGAuthenticity(infoMap.getInt("g_Authenticity"));
            initGameEvent.setGAntiCheat(infoMap.getInt("g_AntiCheat"));
            initGameEvent.setFragLimit(infoMap.getInt("fraglimit"));
            initGameEvent.setDuelFragLimit(infoMap.getInt("duel_fraglimit"));
            initGameEvent.setDmFlags(infoMap.getInt("dmflags"));
            initGameEvent.setDiscord(infoMap.getString("discord"));
            initGameEvent.setCaptureLimit(infoMap.getInt("capturelimit"));
            initGameEvent.setBotMinPlayers(infoMap.getInt("bot_minplayers"));
            initGameEvent.setBgFighterAltControl(infoMap.getInt("bg_fighterAltControl"));
            initGameEvent.setTimeAdd(infoMap.getInt("TimeAdd"));
            initGameEvent.setTkSpec(infoMap.getInt("TK_Spec"));
            initGameEvent.setTkKick(infoMap.getInt("TK_Kick"));
            initGameEvent.setRtvRtm(infoMap.getString("RTVRTM"));

            return initGameEvent;
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
