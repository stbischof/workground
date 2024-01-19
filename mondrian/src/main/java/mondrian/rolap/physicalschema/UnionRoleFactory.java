package mondrian.rolap.physicalschema;

import mondrian.olap.RoleImpl;
import org.eclipse.daanse.olap.api.access.Role;

import java.util.AbstractList;
import java.util.List;
import java.util.Map;

/** Role factory that creates a union role, combining the role from each
 * of a list of role factories. */
public class UnionRoleFactory implements RoleFactory {
    private final List<RoleFactory> factories;

    public UnionRoleFactory(List<RoleFactory> factories) {
        this.factories = factories;
    }

    public Role create(final Map<String, Object> context) {
        return RoleImpl.union(
            new AbstractList<Role>() {
                public Role get(int index) {
                    return factories.get(index).create(context);
                }

                public int size() {
                    return factories.size();
                }
            }
        );
    }
}

