package cn.ttsales.work.core.util.calculate;

import java.util.ArrayList;
import java.util.List;

import cn.ttsales.work.domain.BusUserBonus;

public abstract class AbstractCalculate {

	public AbstractCalculate() {

	}

	public abstract List<BusUserBonus> calculate(List<Object[]> results);

	public static List<BusUserBonus> changeObjectsToBusUserBonuses(
			List<Object[]> results) {
		List<BusUserBonus> busUserBonuses = new ArrayList<BusUserBonus>();
		for (Object[] obj : results) {
			busUserBonuses.add(changeObjectToBusUserBonus(obj));
		}
		return busUserBonuses;
	}

	public static BusUserBonus changeObjectToBusUserBonus(Object[] obj) {
		// TODO
		BusUserBonus busUserBonus = new BusUserBonus();
		return busUserBonus;
	}

}
