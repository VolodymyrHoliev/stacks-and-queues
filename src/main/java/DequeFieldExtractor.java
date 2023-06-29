
import java.lang.reflect.Field;

public class DequeFieldExtractor {
	private final String FIRST_NODE_FIELD_NAME = "first";

	private final String LAST_NODE_FIELD_NAME = "last";

	public <T> T getFirstItem(Deque<?> extractFrom){
		return getNodeValue(extractFrom, FIRST_NODE_FIELD_NAME);
	}

	public <T> T getLastItem(Deque<?> extractFrom) {
		return getNodeValue(extractFrom, LAST_NODE_FIELD_NAME);
	}

	@SuppressWarnings("unchecked")
	private <T> T getNodeValue(Deque<?> extractFrom, String fieldName) {
		Field nodeField;

		T result = null;
		try {
			nodeField = extractFrom.getClass().getDeclaredField(fieldName);

			nodeField.setAccessible(true);

			Object node = nodeField.get(extractFrom);

			Field itemField = node.getClass().getDeclaredField("item");

			itemField.setAccessible(true);

			result = (T) itemField.get(node);
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return result;

	}
}
