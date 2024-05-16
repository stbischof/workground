package org.eclipse.daanse.olap.xmla.bridge.execute;

import org.eclipse.daanse.olap.api.result.AllocationPolicy;

import java.util.List;

public class WriteBackData {

    private List<String> memberUniqueNames;
    private AllocationPolicy allocationPolicy;
    private double newValue;
    private double currentValue;


    public List<String> getMemberUniqueNames() {
        return memberUniqueNames;
    }

    public void setMemberUniqueNames(List<String> memberUniqueNames) {
        this.memberUniqueNames = memberUniqueNames;
    }

    public AllocationPolicy getAllocationPolicy() {
        return allocationPolicy;
    }

    public void setAllocationPolicy(AllocationPolicy allocationPolicy) {
        this.allocationPolicy = allocationPolicy;
    }

    public double getNewValue() {
        return newValue;
    }

    public void setNewValue(double newValue) {
        this.newValue = newValue;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }
}
