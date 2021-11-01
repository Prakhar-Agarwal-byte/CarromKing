package com.entwik.carromcash.models.local;

import com.entwik.carromcash.models.home.LobbyModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LeagueList {

    private static LeagueList instance;
    private static final List<OnLeagueDataChangeListener> onLeagueDataChangeListeners = new ArrayList<>();
    private final Map<String, List<LobbyModel>> lobbyMap = new HashMap<>();

    public static LeagueList getInstance() {
        if(instance == null) {
            instance = new LeagueList();
        }
        return instance;
    }

    public void setLobbies(List<LobbyModel> lobbies) {
        lobbyMap.put("beginner", new ArrayList<>());
        lobbyMap.put("gold", new ArrayList<>());
        lobbyMap.put("silver", new ArrayList<>());
        lobbyMap.put("diamond", new ArrayList<>());
        for (LobbyModel lobby : lobbies) {
            Objects.requireNonNull(lobbyMap.get(lobby.getLevel())).add(lobby);
        }
        for(OnLeagueDataChangeListener listener: onLeagueDataChangeListeners) {
            listener.onDataChange(lobbyMap);
        }
    }

    public void addLeagueDataChangeListener(OnLeagueDataChangeListener listener) {
        onLeagueDataChangeListeners.add(listener);
        listener.onDataChange(lobbyMap);
    }

    public void removeLeagueDataChangeListener(OnLeagueDataChangeListener listener) {
        onLeagueDataChangeListeners.remove(listener);
    }

}
