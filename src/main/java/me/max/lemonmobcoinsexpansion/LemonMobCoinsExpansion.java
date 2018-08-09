/*
 *
 *  *
 *  *  * LemonMobCoinsExpansion - An expansion for the glorious PlaceholderAPI plugin.
 *  *  * Copyright (C) 2018 Max Berkelmans AKA LemmoTresto
 *  *  *
 *  *  * This program is free software: you can redistribute it and/or modify
 *  *  * it under the terms of the GNU General Public License as published by
 *  *  * the Free Software Foundation, either version 3 of the License, or
 *  *  * (at your option) any later version.
 *  *  *
 *  *  * This program is distributed in the hope that it will be useful,
 *  *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  *  * GNU General Public License for more details.
 *  *  *
 *  *  * You should have received a copy of the GNU General Public License
 *  *  * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *  *
 *
 */

package me.max.lemonmobcoinsexpansion;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.max.lemonmobcoins.common.LemonMobCoins;
import me.max.lemonmobcoins.common.api.LemonMobCoinsAPI;
import org.bukkit.OfflinePlayer;

import java.text.NumberFormat;
import java.util.Locale;

@SuppressWarnings("unused")
public class LemonMobCoinsExpansion extends PlaceholderExpansion {

    private LemonMobCoinsAPI api;

    public LemonMobCoinsExpansion(){
        api = LemonMobCoins.getLemonMobCoinsAPI();
    }

    /**
     * We use the LemonMobCoins API so we check if that is null.
     * @return true if the api is not null
     */
    @Override
    public boolean canRegister() {
        return api != null;
    }

    /**
     * @return LemmoTresto which is the name of the author.
     */
    @Override
    public String getAuthor() {
        return "LemmoTresto";
    }

    /**
     * @return String identifier
     */
    @Override
    public String getIdentifier() {
        return "lemonmobcoins";
    }

    /**
     * @return version of our expansion
     */
    @Override
    public String getVersion() {
        return "1.2.0";
    }

    /**
     * This method gets called when we need to parse a placeholder.
     * @return String placeholder
     */
    @Override
    public String onRequest(OfflinePlayer p, String identifier) {
        if (p == null) return null;

        switch(identifier.toLowerCase()){
            case "balance": return String.valueOf(api.getCoinsOfPlayer(p.getUniqueId()));
            case "balance_fixed": return String.valueOf((long) api.getCoinsOfPlayer(p.getUniqueId()));
            case "balance_formatted": return format(api.getCoinsOfPlayer(p.getUniqueId()));
        }

        return null;
    }

    private String format(double d) {
        NumberFormat format = NumberFormat.getInstance(Locale.ENGLISH);
        format.setMaximumFractionDigits(2);
        format.setMinimumFractionDigits(0);
        return format.format(d);
    }
}
