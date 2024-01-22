package mondrian.rolap.physicalschema;

import mondrian.rolap.RolapCube;
import mondrian.rolap.RolapCubeHierarchy;
import mondrian.rolap.RolapHierarchy;
import mondrian.rolap.RolapMember;
import org.eigenbase.xom.ElementDef;


/** Action to assign a default member of a hierarchy. As objects, such
 * actions can be stored in a list and deferred until the cube is
 * sufficiently initialized. */
public abstract class AssignDefaultMember {
    final RolapCube cube;
    final RolapHierarchy hierarchy;
    final ElementDef xml;

    AssignDefaultMember(
        RolapCube cube,
        RolapHierarchy hierarchy,
        ElementDef xml)
    {
        this.cube = cube;
        this.hierarchy = hierarchy;
        this.xml = xml;
    }

    /** Resolve default member based on real members. Calculated members are
     * not available yet. */
    abstract AssignDefaultMember apply();

    /** Assigns default member based on calculated members. */
    abstract void apply2();

    protected void setDefaultMember(
        RolapCubeHierarchy hierarchy,
        RolapMember member)
    {
        hierarchy.setDefaultMember(member);
    }

}
