package org.gmnz.vega;


import java.util.List;
import java.util.Random;

import javax.sql.DataSource;

import org.gmnz.vega.domain.Category;
import org.gmnz.vega.service.AllergenService;
import org.gmnz.vega.service.CategoryService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;


public class CategoryServiceTest {

	private static Vega vega;



	@BeforeClass
	public static void initApplicationContext() {
		VegaSpringUtil.initSpring();
		vega = VegaFactory.getFactory().buildVega();
	}



	@Test
	public void getAllCategories() throws VegaException {
		CategoryService svc = vega.getCategoryService();
		Assert.assertNotNull(svc.getAllCategories());
	}



	@Test
	public void getAllCategoriesWithAllergens() throws VegaException {
		CategoryService svc = vega.getCategoryService();
		List<Category> categories = svc.getAllCategoriesWithAllergens();
		System.out.println(categories);
	}



	@Test(expected = VegaException.class)
	public void getCategoryByIdWithNull() throws VegaException {
		vega.getCategoryService().getCategoryById(null);
	}



	@Test(expected = VegaException.class)
	public void getCategoryByIdWithEmpty() throws VegaException {
		vega.getCategoryService().getCategoryById("");
	}



	@Test
	public void getCategoryByIdWithWrongId() throws VegaException {
		Category c = vega.getCategoryService().getCategoryById("wrong-id");
		Assert.assertNull(c);
	}



	@Test
	public void getCategoryById() throws VegaException {
		CategoryService svc = vega.getCategoryService();
		List<Category> categories = svc.getAllCategoriesWithAllergens();
		int numCategories = categories.size();
		int randomCategoryIndex = new Random().nextInt(numCategories);
		Category expected = categories.get(randomCategoryIndex);
		System.out.println("expected category : " + expected);
		String id = expected.getId();
		Category actual = svc.getCategoryById(id);
		System.out.println("actual category : " + actual);
		Assert.assertEquals(expected, actual);
	}



	@Test(expected = VegaException.class)
	public void createCategoryWNull() throws VegaException {
		CategoryService svc = vega.getCategoryService();
		svc.createCategory(null);
	}



	@Test(expected = VegaException.class)
	public void createCategoryWEmpty() throws VegaException {
		CategoryService svc = vega.getCategoryService();
		svc.createCategory("");
	}



	@Test
	public void createCategoryAlreadyExists() throws VegaException {
		CategoryService svc = vega.getCategoryService();
		List<Category> categories = svc.getAllCategoriesWithAllergens();
		int numCategories = categories.size();
		int randomCategoryIndex = new Random().nextInt(numCategories);
		Category dupe = categories.get(randomCategoryIndex);
		String categoryName = dupe.getName();
		String dupeId = svc.createCategory(categoryName);
		Assert.assertEquals(dupe.getId(), dupeId);
	}



	@Test
	public void createCategory() throws VegaException {
		CategoryService svc = vega.getCategoryService();
		String newCategoryId = svc.createCategory("sample");
		Category actual = svc.getCategoryById(newCategoryId);
		Assert.assertEquals(newCategoryId, actual.getId());

		deleteCategoryById(newCategoryId);
	}



	@Test(expected = VegaException.class)
	public void changeCategoryNameNullId() throws VegaException {
		CategoryService svc = vega.getCategoryService();
		svc.changeCategoryName(null, "bohpe");
	}



	@Test(expected = VegaException.class)
	public void changeCategoryNameNullName() throws VegaException {
		CategoryService svc = vega.getCategoryService();
		svc.changeCategoryName("cucu", null);
	}



	@Test
	public void changeCategoryNameInvalidTarget() throws VegaException {

		String srcCatName = "cat_source";
		String tgtCatName = "cat_target";

		CategoryService svc = vega.getCategoryService();
		String srcCatId = svc.createCategory(srcCatName);
		String tgtCatId = svc.createCategory(tgtCatName);

		try {
			svc.changeCategoryName(srcCatId, tgtCatName);
		} catch (VegaException e) {
			System.err.println("expected exception received -- " + e.getMessage());
			return;
		} finally {
			deleteCategoryById(srcCatId);
			deleteCategoryById(tgtCatId);
		}
		Assert.fail("it was possible to change the name of a category to an already existing one");
	}



	@Test
	public void changeCategory() throws VegaException {
		CategoryService svc = vega.getCategoryService();

		String dummyCategoryName = "dummyCategory";
		String newCategoryName = "dummyRenamedCategory";
		String newCategoryId = svc.createCategory(dummyCategoryName);
		svc.changeCategoryName(newCategoryId, newCategoryName);

		Category actualCategory = svc.getCategoryById(newCategoryId);
		Assert.assertEquals(newCategoryName, actualCategory.getName());

		deleteCategoryById(newCategoryId);

	}



	@Test(expected = VegaException.class)
	public void removeCategoryNullId() throws VegaException {
		CategoryService svc = vega.getCategoryService();
		svc.removeCategory(null);
	}



	@Test(expected = VegaException.class)
	public void removeCategoryEmptyId() throws VegaException {
		CategoryService svc = vega.getCategoryService();
		svc.removeCategory("");
	}



	@Test
	public void removeCategorySimple() throws VegaException {
		CategoryService svc = vega.getCategoryService();
		String dummyCategoryId = null;
		try {
			dummyCategoryId = svc.createCategory("dummyCategory");
			svc.removeCategory(dummyCategoryId);

			Category actual = svc.getCategoryById(dummyCategoryId);
			Assert.assertNull(actual);
		} finally {
			if (dummyCategoryId != null) {
				deleteCategoryById(dummyCategoryId);
			}
		}
	}



	@Test(expected = VegaException.class)
	public void removeCategoryWithAllergens() throws VegaException {
		CategoryService categoryService = vega.getCategoryService();
		AllergenService allergenService = vega.getAllergenService();
		String nonEmptyCategoryId = null;
		String testAllergen1 = null;
		String testAllergen2 = null;
		try {
			String categoryName = "non-empty-category";
			nonEmptyCategoryId = categoryService.createCategory(categoryName);
			testAllergen1 = allergenService.createAllergen("test-a-1", nonEmptyCategoryId);
			testAllergen2 = allergenService.createAllergen("test-a-2", nonEmptyCategoryId);

			categoryService.removeCategory(nonEmptyCategoryId);
		} finally {
			ApplicationContext ctx = VegaSpringUtil.getSpringContext();
			JdbcTemplate tpl = new JdbcTemplate(ctx.getBean("dataSource", DataSource.class));
			tpl.update("DELETE FROM allergen WHERE id = ?", testAllergen1);
			tpl.update("DELETE FROM allergen WHERE id = ?", testAllergen2);
			deleteCategoryById(nonEmptyCategoryId);
		}
	}



	private void deleteCategoryById(String id) {
		ApplicationContext ctx = VegaSpringUtil.getSpringContext();
		JdbcTemplate tpl = new JdbcTemplate(ctx.getBean("dataSource", DataSource.class));
		tpl.update("DELETE FROM category WHERE id = ?", id);
	}

}
