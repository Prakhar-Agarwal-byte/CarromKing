package com.entwik.carromcash.models.local;


import com.entwik.carromcash.models.home.LobbyModel;

import java.util.List;
import java.util.Map;

public interface OnLeagueDataChangeListener {
    void onDataChange(Map<String, List<LobbyModel>> lobbies);
}
