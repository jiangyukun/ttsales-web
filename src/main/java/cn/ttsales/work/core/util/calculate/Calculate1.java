package cn.ttsales.work.core.util.calculate;

import java.util.List;

import cn.ttsales.work.domain.BusUserBonus;

public class Calculate1 extends AbstractCalculate {

	@Override
	public List<BusUserBonus> calculate(List<Object[]> results) {
		return AbstractCalculate.changeObjectsToBusUserBonuses(results);
	}
	
}
