package model;

import bean.Collateral;

import java.util.List;

public interface CollateralModel {
    /**
     * add collateral
     * @param collateral
     * @return
     */
    boolean addCollateral(Collateral collateral);

    /**
     * query all collateral
     * @param uID
     * @return
     */
    List<Collateral> queryAllCollateral(int uID);

    /**
     * delete collateral
     * @param cID
     * @return
     */
    boolean deleteCollateral(int cID);

}
