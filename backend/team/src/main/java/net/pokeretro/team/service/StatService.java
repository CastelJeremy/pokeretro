package net.pokeretro.team.service;

import org.springframework.stereotype.Service;

import net.pokeretro.team.entity.Stat;

@Service
public class StatService {
    private Integer processHp(Integer level, Integer baseHp, Integer individualHp, Integer effortHp) {
        Integer hp = (baseHp + individualHp) * 2;
        hp += (int) Math.floor(Math.sqrt(effortHp) / 4);
        hp *= 4;
        hp = (int) Math.floor(hp / 100);
        hp += level + 10;

        return hp;
    }

    private Integer processOther(Integer level, Integer baseStat, Integer individualStat, Integer effortStat) {
        Integer stat = (baseStat + individualStat) * 2;
        stat += (int) Math.floor(Math.sqrt(effortStat) / 4);
        stat *= 4;
        stat = (int) Math.floor(stat / 100);
        stat += 5;

        return stat;
    }

    public Stat processStat(Integer level, Stat baseStat, Stat individualStat, Stat effortStat) {
        return new Stat(
                processHp(level, baseStat.getHp(), individualStat.getHp(), effortStat.getHp()),
                processOther(level, baseStat.getAttack(), individualStat.getAttack(), effortStat.getAttack()),
                processOther(level, baseStat.getDefense(), individualStat.getDefense(), effortStat.getDefense()),
                processOther(level, baseStat.getSpeed(), individualStat.getSpeed(), effortStat.getSpeed()),
                processOther(level, baseStat.getSpecial(), individualStat.getHp(), effortStat.getSpecial()));
    }
}
