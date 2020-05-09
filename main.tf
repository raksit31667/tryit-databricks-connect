resource "azurerm_resource_group" "rg-databricks" {
  name     = "rg-databricks"
  location = "West US"
}

resource "azurerm_databricks_workspace" "databricks-connect-test" {
  name                = "databricks-connect-test"
  resource_group_name = azurerm_resource_group.rg-databricks.name
  location            = azurerm_resource_group.rg-databricks.location
  sku                 = "standard"

  tags = {
    Environment = "Development"
  }
}

resource "azurerm_storage_account" "storage-account-test" {
  account_replication_type = "GRS"
  account_tier             = "Standard"
  location                 = azurerm_resource_group.rg-databricks.location
  name                     = "databricksconnecttest"
  resource_group_name      = azurerm_resource_group.rg-databricks.name
}

resource "azurerm_storage_container" "storage-container-test" {
  name                 = "radio"
  storage_account_name = azurerm_storage_account.storage-account-test.name
}