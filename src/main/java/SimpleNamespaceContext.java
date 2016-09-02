import java.util.HashMap;
import java.util.Collection;

public class SimpleNamespaceContext {

	private HashMap<String, SimpleNamespaceContext.Namespace> namespaces = new HashMap<>();

	public Namespace addNamespace(String namespaceURI, String prefix) {
		Namespace newNS = new Namespace(namespaceURI, prefix);

		Namespace existingNS = namespaces.put(namespaceURI, newNS);

		if (existingNS != null) {
			namespaces.put(namespaceURI, existingNS);
			return existingNS;
		}

		return newNS;			
	}

	public Collection<SimpleNamespaceContext.Namespace> getNamespaces() {
		return namespaces.values();
	}

	public class Namespace {
		public final String uri;
		public final String prefix;

		private Namespace(String namespaceURI, String prefix) {
			this.uri = namespaceURI;
			this.prefix = prefix;
		}
	}
}
